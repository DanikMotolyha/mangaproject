package by.motolyha.mangaproject.model.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool  {
    private static final Logger logger = LogManager.getLogger();
    private static ConnectionPool instance = new ConnectionPool();
    private static final ReentrantLock lock = new ReentrantLock();
    private static final AtomicBoolean isPoolCreated = new AtomicBoolean(false);
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> usedConnections;
    private static final int POOL_SIZE;

    static {
        DatabaseConfiguration configuration = DatabaseConfiguration.getInstance();
        POOL_SIZE = configuration.getPoolSize();
    }

    private ConnectionPool() {
    }

    public void initPool() {
        usedConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionFactory.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.offer(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR,"can't create connection, exception: ", e);
            }
        }
        if (freeConnections.isEmpty()) {
            logger.log(Level.FATAL,"can't create connections");
            throw new RuntimeException("can't create connections");
        }
    }

    public static ConnectionPool getInstance() {
        if (!isPoolCreated.get()) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                isPoolCreated.getAndSet(true);
            }
            lock.unlock();
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            usedConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            logger.log(Level.FATAL, "foreign connection is detected");
            throw new RuntimeException("foreign connection is detected : " + connection);
        }
        usedConnections.remove(connection);
        try {
            freeConnections.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            logger.log(Level.FATAL, "InterruptedException ", e);
            Thread.currentThread().interrupt();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().completeClose();
            } catch (InterruptedException | SQLException e) {
                logger.log(Level.ERROR,"Connection isn't deleted");
            }
        }
        deregisterDrivers();
    }


    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            var driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR,"drivers aren't' deregister, driver: {}, exception: {}", driver, e);
            }
        }
    }
}

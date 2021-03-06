package by.motolyha.mangaproject.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        DatabaseConfiguration configuration = DatabaseConfiguration.getInstance();
        String driverName = configuration.getDbDriver();
        DATABASE_URL = configuration.getUrl();
        properties.put("user", configuration.getUser());
        properties.put("password", configuration.getPassword());
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            String loggerMessage = "can't registry driver: " + driverName +
                    " with exception: " + e;
            logger.log(Level.FATAL, loggerMessage);
            throw new RuntimeException("fatal: can't registry driver: " + driverName, e);
        }
    }

    private ConnectionFactory() {
    }

    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}

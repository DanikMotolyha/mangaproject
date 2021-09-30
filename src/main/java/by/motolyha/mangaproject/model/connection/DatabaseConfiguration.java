package by.motolyha.mangaproject.model.connection;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class DatabaseConfiguration {

    private static final Logger logger = LogManager.getLogger(DatabaseConfiguration.class);
    private static final String DATABASE_PATH = "database";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_POOL_SIZE = "db.poolSize";

    private final String url;
    private final String user;
    private final String password;
    private final String dbDriver;
    private final int poolSize;

    private static DatabaseConfiguration instance;

    public static DatabaseConfiguration getInstance() {
        if (instance == null) {
            instance = init();
        }
        return instance;
    }

    private DatabaseConfiguration(
            String url,
            String user,
            String password,
            String dbDriver,
            int poolSize
    ) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.dbDriver = dbDriver;
        this.poolSize = poolSize;
    }

    private static DatabaseConfiguration init() {
        try {

            var resourceBundle = ResourceBundle.getBundle(DATABASE_PATH);

            var url = resourceBundle.getString(DB_URL);
            var user = resourceBundle.getString(DB_USER);
            var password = resourceBundle.getString(DB_PASSWORD);
            var dbDriver = resourceBundle.getString(DB_DRIVER);
            var poolSize = Integer.parseInt(resourceBundle.getString(DB_POOL_SIZE));
            if (poolSize < 1 || poolSize > 24) {
                logger.log(Level.FATAL, "pool size can't be less than 1 and more than 25: " + poolSize);
                throw new RuntimeException("pool size can't be less than 1 and more than 25");
            }

            return new DatabaseConfiguration(url, user, password, dbDriver, poolSize);

        } catch (MissingResourceException e) {
            logger.log(Level.FATAL, "missing required resource, details: ", e);
            throw new RuntimeException("missing required resource", e);
        } catch (NumberFormatException e) {
            logger.log(Level.FATAL, "can't parse number, details: ", e);
            throw new RuntimeException("can't parse number", e);
        }
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getUrl() {
        return url;
    }
}

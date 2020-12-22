package db;

import com.sun.tools.javac.Main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static Connection connection = null;
    private final static String USERNAME = "elizeiaz";
    private final static String PASSWORD = "elizeiaz";
    private final static String URL = "jdbc:mysql://localhost:3306/welook?serverTimezone=Europe/Moscow";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection connectToDB() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Can't find driver", e);
            return null;
        }

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("Successful connection to db");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Can't connect to db", e);
            return null;
        }
        return connection;
    }

    public Boolean closeConnection() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Connection not closed", e);
        }
        return false;
    }
}

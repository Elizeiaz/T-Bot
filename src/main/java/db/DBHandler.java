package db;

import com.sun.tools.javac.Main;

import java.sql.*;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHandler {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static Connection connection = null;
    private final static String USERNAME = "elizeiaz";
    private final static String PASSWORD = "elizeiaz";
    private final static String URL = "jdbc:mysql://localhost:3306/welook?serverTimezone=Europe/Moscow&useSSL=false";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection connectToDB() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "DB: Can't find driver", e);
            return null;
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("DB: Successful connection to db");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "DB: Can't connect to db", e);
            return null;
        }
        return connection;
    }

    public Boolean closeConnection() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "DB: Connection not closed", e);
        }
        return false;
    }


//    public boolean checkTableExist(Connection connection, String tableName){
//        try {
//            DatabaseMetaData metaData = connection.getMetaData();
//            ResultSet rs = metaData.getTables(null, null, "%", null);
//            while (rs.next()){
//                logger.info(rs.getString("TABLE_NAME"));
//            }
//        } catch (SQLException e) {
//            logger.log(Level.WARNING, "DB: can't check", e);
//        }
//
//        return false;
//    }

    public boolean createNewTable(Connection connection, String tableName, String tableFields) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(MessageFormat.format(
                    "CREATE TABLE IF NOT EXISTS {0} ({1})", tableName, tableFields));
            return true;
        } catch (SQLException e) {
            logger.log(Level.WARNING, MessageFormat.format("DB: can't create new table - {0}", tableName), e);
            return false;
        }
    }

    public boolean deleteTable(Connection connection, String tableName) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(MessageFormat.format("DROP TABLE {0}", tableName));
            return true;
        } catch (SQLException e) {
            logger.log(Level.WARNING, MessageFormat.format("DB: can't delete table - {0}", tableName), e);
            return false;
        }
    }
}

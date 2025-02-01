package dataBase;

import utils.PropertyLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {


    private static final String URL = PropertyLoader.getProperty("db.url");
    private static final String USER = PropertyLoader.getProperty("db.user");
    private static final String PASSWORD = PropertyLoader.getProperty("db.password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}

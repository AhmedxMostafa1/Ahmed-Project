package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7726721?useSSL=false&serverTimezone=UTC";
    private static final String USER = "sql7726721";
    private static final String PASSWORD = "s99ykVrHfC";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

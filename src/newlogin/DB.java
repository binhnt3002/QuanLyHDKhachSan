package newlogin;
import java.sql.*;


public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/logindb";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
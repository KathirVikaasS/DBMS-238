package campusmarketplace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Make these static so static methods can access them
    private static final String url = "jdbc:mysql://localhost:3306/campus_marketplace?useSSL=false&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "Gowri29mus@";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Static method to get DB connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Quick test
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) System.out.println("✅ DB Connected!");
            else System.out.println("❌ DB not connected.");
        } catch (SQLException e) {
            System.out.println("❌ Connection failed:");
            e.printStackTrace();
        }
    }
}

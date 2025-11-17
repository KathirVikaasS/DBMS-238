package campusmarketplace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    // -----------------------------
    // Insert a new item
    public static boolean insertItem(Item item) {
        String sql = "INSERT INTO Items (name, category, price, description, sellerId) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getCategory());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setString(4, item.getDescription());
            pstmt.setInt(5, item.getSellerId());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // -----------------------------
    // Fetch all items with seller names
    public static List<String[]> getAllItems() {
        List<String[]> items = new ArrayList<>();
        String sql = "SELECT i.itemId, i.name, i.category, i.price, i.description, u.name AS sellerName " +
                "FROM Items i JOIN Users u ON i.sellerId = u.userId";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String[] row = new String[6];
                row[0] = String.valueOf(rs.getInt("itemId"));  // updated column name
                row[1] = rs.getString("name");
                row[2] = rs.getString("category");
                row[3] = String.valueOf(rs.getDouble("price"));
                row[4] = rs.getString("description");
                row[5] = rs.getString("sellerName");
                items.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    // -----------------------------
    // Buy an item (delete it for simplicity)
    public static boolean buyItem(int itemId) {
        String sql = "DELETE FROM Items WHERE itemId = ?";  // updated column name
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, itemId);
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

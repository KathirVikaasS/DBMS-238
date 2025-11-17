package campusmarketplace;

public class TestItem {

    public static void main(String[] args) {
        // Example: sellerId = 1 (make sure this user exists in DB)
        Item item = new Item("Laptop", "Electronics", 45000.0, "Good condition laptop", 1);

        if (ItemDAO.insertItem(item)) {
            System.out.println("✅ Item inserted successfully!");
        } else {
            System.out.println("❌ Failed to insert item.");
        }
    }
}

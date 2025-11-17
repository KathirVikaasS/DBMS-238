package campusmarketplace;

public class TestUser {

    public static void main(String[] args) {
        User user = new User("Kathir", "kathir@example.com", "9876543210");

        if (UserDAO.insertUser(user)) {
            System.out.println("✅ User inserted successfully!");
        } else {
            System.out.println("❌ Failed to insert user.");
        }
    }
}

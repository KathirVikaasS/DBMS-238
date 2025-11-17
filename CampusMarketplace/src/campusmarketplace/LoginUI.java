package campusmarketplace;

import javax.swing.*;

public class LoginUI {

    private static User currentUser = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginUI::showLoginForm);
    }

    public static void showLoginForm() {
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] message = {
                "Email:", emailField,
                "Password:", passwordField
        };

        // Show options: Login or Register
        int option = JOptionPane.showOptionDialog(
                null,
                message,
                "Login",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Login", "Register", "Cancel"},
                "Login"
        );

        if (option == 0) { // Login
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            currentUser = UserDAO.login(email, password);

            if (currentUser != null) {
                JOptionPane.showMessageDialog(null,
                        "✅ Login successful! Welcome " + currentUser.getName());
                new MainUI(currentUser).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,
                        "❌ Invalid email or password");
                showLoginForm(); // retry
            }

        } else if (option == 1) { // Register
            showRegisterForm(); // call new register method
            showLoginForm();    // return to login after registration

        } else { // Cancel
            JOptionPane.showMessageDialog(null, "Login cancelled. Exiting.");
            System.exit(0);
        }
    }

    // -----------------------------
    // Register User method
    public static void showRegisterForm() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField contactField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] message = {
                "Name:", nameField,
                "Email:", emailField,
                "Contact:", contactField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Register User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String email = emailField.getText();
            String contact = contactField.getText();
            String password = new String(passwordField.getPassword());

            // Validate input
            if (name.isEmpty() || email.isEmpty() || contact.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "❌ All fields are required!");
                showRegisterForm(); // retry
                return;
            }

            User user = new User(name, email, contact, password);
            if (UserDAO.insertUser(user)) {
                JOptionPane.showMessageDialog(null, "✅ User registered successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Failed to register user. Email may already exist.");
            }
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}

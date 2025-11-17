package campusmarketplace;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainUI extends JFrame {

    private User currentUser; // logged-in user

    public MainUI(User currentUser) {
        this.currentUser = currentUser;

        setTitle("Campus Marketplace - Welcome " + currentUser.getName());
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Buttons
        JButton listItemBtn = new JButton("List Item");
        JButton viewItemsBtn = new JButton("View Items");
        JButton buyItemBtn = new JButton("Buy Item");

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(listItemBtn);
        panel.add(viewItemsBtn);
        panel.add(buyItemBtn);
        add(panel);

        // -----------------------------
        // List Item action
        listItemBtn.addActionListener(e -> {
            JTextField nameField = new JTextField();
            JTextField categoryField = new JTextField();
            JTextField priceField = new JTextField();
            JTextField descriptionField = new JTextField();

            Object[] message = {
                    "Item Name:", nameField,
                    "Category:", categoryField,
                    "Price:", priceField,
                    "Description:", descriptionField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "List Item", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    String name = nameField.getText();
                    String category = categoryField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    String description = descriptionField.getText();

                    // Use logged-in user's ID automatically
                    int sellerId = currentUser.getUserId();

                    Item item = new Item(name, category, price, description, sellerId);

                    if (ItemDAO.insertItem(item)) {
                        JOptionPane.showMessageDialog(null, "✅ Item listed successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Failed to list item.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "❌ Invalid number format for Price.");
                }
            }
        });

        // -----------------------------
        // View Items action
        viewItemsBtn.addActionListener(e -> {
            List<String[]> items = ItemDAO.getAllItems();
            if (items.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No items available.");
                return;
            }

            String[] columns = {"ID", "Name", "Category", "Price", "Description", "Seller"};
            String[][] data = new String[items.size()][6];
            for (int i = 0; i < items.size(); i++) {
                data[i] = items.get(i);
            }

            JTable table = new JTable(data, columns);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);

            JOptionPane.showMessageDialog(null, scrollPane, "Available Items", JOptionPane.INFORMATION_MESSAGE);
        });

        // -----------------------------
        // Buy Item action
        buyItemBtn.addActionListener(e -> {
            List<String[]> items = ItemDAO.getAllItems();
            if (items.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No items available to buy.");
                return;
            }

            StringBuilder itemList = new StringBuilder("Available Items:\n\n");
            for (String[] item : items) {
                itemList.append("ID: ").append(item[0])
                        .append(" | Name: ").append(item[1])
                        .append(" | Price: ").append(item[3])
                        .append(" | Seller: ").append(item[5]).append("\n");
            }

            String input = JOptionPane.showInputDialog(null, itemList + "\nEnter the ID of the item you want to buy:");
            if (input != null && !input.isEmpty()) {
                try {
                    int itemId = Integer.parseInt(input);
                    if (ItemDAO.buyItem(itemId)) {
                        JOptionPane.showMessageDialog(null, "✅ Item purchased successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Failed to purchase item. Invalid ID?");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "❌ Invalid number format for item ID.");
                }
            }
        });
    }

    // Optional: static helper to show MainUI
    public static void show(User currentUser) {
        SwingUtilities.invokeLater(() -> new MainUI(currentUser).setVisible(true));
    }
}

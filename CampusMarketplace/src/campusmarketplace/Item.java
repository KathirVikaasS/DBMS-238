package campusmarketplace;

public class Item {

    private int itemId;
    private String name;
    private String category;
    private double price;
    private String description;
    private int sellerId;
    private String status;

    // Constructor for adding a new item (no ID yet)
    public Item(String name, String category, double price, String description, int sellerId) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.sellerId = sellerId;
        this.status = "Available";
    }

    // Constructor with all fields (for retrieving from DB)
    public Item(int itemId, String name, String category, double price, String description, int sellerId, String status) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.sellerId = sellerId;
        this.status = status;
    }

    // Getters & setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

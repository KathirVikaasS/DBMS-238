package campusmarketplace;

public class User {
    private int userId;
    private String name;
    private String email;
    private String contact;

    // Constructor without ID (for inserting new users)
    public User(String name, String email, String contact) {
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    // Constructor with ID (for retrieving from DB)
    public User(int userId, String name, String email, String contact) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    // Getters & setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    //*****
    private String password;

    public User(String name, String email, String contact, String password) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
    }

    // Constructor with ID
    public User(int userId, String name, String email, String contact, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
    }

    // Getter & setter for password
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
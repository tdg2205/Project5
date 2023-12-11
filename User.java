/**
 * User.java
 * <p>
 * This is a User.java class which contains all the fields, getters and setters method for the user.
 *
 * @author Tyler Gentry, lab sec 30
 * @version 11 November, 2023
 */
public class User {
    private String email;
    private String password;
    private String username;
    private boolean seller;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSeller(boolean seller) {
        this.seller = seller;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isSeller() {
        return seller;
    }

    public User(String email, String password, String username, boolean seller) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.seller = seller;
    }
}

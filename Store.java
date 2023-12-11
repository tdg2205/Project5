import java.util.ArrayList;
/**
 * Store.java
 * <p>
 * This is a Store.java class which contains all the fields, getters and setters method for the store.
 *
 * @author Tyler Gentry and Krish Sharma, lab sec 30
 * @version 11 November, 2023
 */
public class Store {
    private ArrayList<Product> storeProducts;
    private String storeName;
    private User owner;
    private ArrayList<Customer> purchases;


    public void setProducts(ArrayList<Product> products) {
        this.storeProducts = products;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setPurchases(ArrayList<Customer> purchases) {
        this.purchases = purchases;
    }

    public ArrayList<Product> getProducts() {
        return storeProducts;
    }

    public String getStoreName() {
        return storeName;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Customer> getPurchases() {
        return purchases;
    }

    public Store(String storeName, User owner) {
        this.storeProducts = new ArrayList<>();
        this.storeName = storeName;
        this.owner = owner;
        this.purchases = new ArrayList<>();
    }
}

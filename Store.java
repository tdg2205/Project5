import java.util.ArrayList;
public class Store {
    private ArrayList<Product> storeProducts;
    private  String storeName;
    private User owner;

    public void setProducts(ArrayList<Product> products) {
        this.storeProducts = products;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Store(ArrayList<Product> products, String storeName, User owner) {
        this.storeProducts = products;
        this.storeName = storeName;
        this.owner = owner;
    }
}

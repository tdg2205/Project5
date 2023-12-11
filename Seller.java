import java.util.ArrayList;
/**
 * Seller.java
 * <p>
 * This is a Seller.java class which contains all the fields, getters and setters method
 * with methods to edit and remove products along with a method to create stores
 *
 * @author Lohit Jagarlamudi and Tyler Gentry, lab sec 30
 * @version 11 November, 2023
 */
public class Seller extends User {
    public ArrayList<Store> yourStores;

    public Seller(String email, String password, String username) {
        super(email, password, username, true);
        yourStores = new ArrayList<Store>();
    }

    public void setStores(ArrayList<Store> stores) {
        yourStores = stores;
    }

    public ArrayList<Store> getStores() {
        return yourStores;
    }

    public ArrayList<Store> getYourStores() {
        return yourStores;
    }


    public void createYourStore(Store store) {
        yourStores.add(store);
    }

    public static void addProduct(Product product, Store store) {
        store.getProducts().add(product);
    }

    public static void removeProduct(Product product, Store store) {
        if (store.getProducts().isEmpty()) {
            System.out.println("No products in store");
        } else {
            for (Product p : store.getProducts()) {
                if (p.equals(product)) {
                    store.getProducts().remove(product);
                    return;
                }
            }
            System.out.println("No matching product found");
        }
    }

    public static void editProduct(Product product, Store store,
                                   String newName, String newDescription, int newQuantity, double newPrice) {
        if (store.getProducts().isEmpty()) {
            System.out.println("No products in store");
        } else {
            for (Product p : store.getProducts()) {
                if (p.equals(product)) {
                    p.setProductName(newName);
                    p.setDescription(newDescription);
                    p.setQuantity(newQuantity);
                    p.setPrice(newPrice);
                    return;
                }
            }
            System.out.println("No matching product found");
        }
    }
}

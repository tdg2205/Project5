import java.util.ArrayList;

public class Seller extends User{
    public static ArrayList<Store> yourStores;

    public Seller(String email, String password, String username) {
        super(email, password, username, true);
        yourStores = new ArrayList<Store>();
    }

    public void setStores(ArrayList<Store> stores) {
        yourStores = stores;
    }
    public ArrayList<Store> getStores() {
        return  yourStores;
    }

    public static ArrayList<Store> getYourStores() {
        return yourStores;
    }


    public void createYourStore(Store store){
        yourStores.add(store);
    }
    public static void addProduct(Product product, Store store) {
        store.getProducts().add(product);
    }
    public static void removeProduct(Product product, Store store) {
        if (store.getProducts().isEmpty()) {
            System.out.println("No products in store");
        } else {
            for (Product p: store.getProducts()) {
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
            for (Product p: store.getProducts()) {
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

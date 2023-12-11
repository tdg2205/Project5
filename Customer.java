import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Customer.java
 * <p>
 * This is a Customer.java class which contains all the fields, getters and setters method
 * along with methods to purchase a product and search for a product.
 *
 * @author Tyler Gentry, lab sec 30
 * @version 11 November, 2023
 */
public class Customer extends User {
    private int purchaseCount;
    private ArrayList<Product> purchasedItems;
    private ArrayList<Product> shoppingCart;


    public Customer(String email, String password, String username) {
        super(email, password, username, false);
        shoppingCart = new ArrayList<>();
        purchasedItems = new ArrayList<>();
    }


    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public void setPurchasedItems(ArrayList<Product> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public void setShoppingCart(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public ArrayList<Product> getPurchasedItems() {
        return purchasedItems;
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void addToShoppingCart(Product product) {
        shoppingCart.add(product);

    }

    public boolean purchaseProduct(Marketplace marketplace, Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            purchasedItems.add(product);
            purchaseCount++;
            product.setSales(+quantity);
            for (Store store : marketplace.getStores()) {
                if (store.getStoreName().equals(product.getProductStoreName())) {
                }
            }
            System.out.println("Purchase successful! " +
                    quantity + " units of " + product.getProductName() +
                    " bought for " + (quantity * product.getPrice()));
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Product> searchProducts(List<Product> marketplace, String searchTerm) {
        ArrayList<Product> matchingProducts = new ArrayList<>();
        for (Product product : marketplace) {
            if (product.getProductName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    product.getProductStoreName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    product.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }


}

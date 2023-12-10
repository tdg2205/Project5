import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Customer extends User{
    private static int purchaseCount;
    private static ArrayList<Product> purchasedItems;
    private static ArrayList<Product> shoppingCart;


    public Customer(String email, String password, String username) {
        super(email, password, username, false);
    }


    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public void setPurchasedItems(ArrayList<Product> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public static void setShoppingCart(ArrayList<Product> shoppingCart) {
        Customer.shoppingCart = shoppingCart;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public ArrayList<Product> getPurchasedItems() {
        return purchasedItems;
    }

    public  ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }
    public void addToShoppingCart(Product product) {
        shoppingCart.add(product);

    }
    public void purchaseProduct(Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            purchasedItems.add(product);
            purchaseCount++;
            product.setSales(+quantity);
            for (Store store:Marketplace.getStores()) {
                if (store.getStoreName().equals(product.getProductStoreName())) {

                }
            }
            System.out.println("Purchase successful! " +
                    quantity + " units of " + product.getProductName() +
                    " bought for " + (quantity * product.getPrice()));
        } else {
            System.out.println("Insufficient quantity available.");
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

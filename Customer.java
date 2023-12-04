import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Customer extends User{
    private int purchaseCount;
    private ArrayList<Product> purchasedItems;

    public Customer(String email, String password, String username) {
        super(email, password, username, false);
    }


    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public void setPurchasedItems(ArrayList<Product> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public ArrayList<Product> getPurchasedItems() {
        return purchasedItems;
    }

    public void purchaseProduct(Product product) {

    }

    public List<Product> searchProducts(List<Product> marketplace, String searchTerm) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : marketplace) {
            if (product.getItemName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    product.getStore().getStoreName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    product.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    public void sortQuantity() {
        Comparator<Product> quantityComparator = Comparator.comparingInt(Product::getQuantity);
        ArrayList<Product> sortedProducts = Collections.sort(Marketplace.getProducts(),quantityComparator);
    }

    public void sortPrice() {
        Comparator<Product> priceComparator = Comparator.comparingDouble(Product::getPrice);
        ArrayList<Product> sortedProducts = Collections.sort(Marketplace.getProducts(),priceComparator);

    }

}

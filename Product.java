import java.util.ArrayList;
import java.util.List;
/**
 * Product.java
 *
 * This is a Product.java class which contains all the fields, getters and setters method along with increasing purchase count and updating quanity methods.
 *
 * @author Krish Sharma, lab sec 30
 *
 * @version 11 November, 2023
 *
 */
class Product {
    private String productName;
    private String description;
    private int quantity;
    private double price;
    private String productStoreName;
    private int sales;

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductStoreName(String productStoreName) {
        this.productStoreName = productStoreName;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public Product(String productName, String description, int quantity, double price, String productStoreName) {
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.productStoreName = productStoreName;
    }

    public String getProductName() {
        return productName;
    }


    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getProductStoreName() {
        return productStoreName;
    }

    public int getSales() {
        return sales;
    }
}

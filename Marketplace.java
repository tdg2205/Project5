import java.io.*;
import java.util.*;
/**
 * Store.java
 * <p>
 * This is a Store.java class which contains all the fields, getters and setters method along with increasing purchase count and updating quanity methods.
 *
 * @author Tyler Gentry and Krish Sharma, lab sec 30
 * @version 11 November, 2023
 */
public class Marketplace {

    private ArrayList<Product> products;
    private ArrayList<Store> stores;
    private ArrayList<User> users;

    public User getCurrentUser() {
        return currentUser;
    }

    private User currentUser;

    public Marketplace() {
        products = new ArrayList<>();
        stores = new ArrayList<>();
        users = new ArrayList<>();
        readUser();
        readStore();
        readProduct();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public ArrayList<User> getUsers() {
        return users;
    }


    public void readProduct() {
        try (BufferedReader br = new BufferedReader(new FileReader("ProductDatabase.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split("\\|");
                String name = productData[0];
                String description = productData[1];
                int quantity = Integer.parseInt(productData[2]);
                double price = Double.parseDouble(productData[3]);
                String storeName = productData[4];
                Product product = new Product(name, description, quantity, price, storeName);
                Seller seller = (Seller) currentUser;
                for (Store wantedStore : seller.getYourStores()) {
                    if (wantedStore.getStoreName().equals(storeName)) {
                        wantedStore.getProducts().add(product);
                        this.products.add(product);
                        System.out.println("check : " + product.getProductName());
                    }
                }
                System.out.println("Products Loaded");
            }
        } catch (IOException e) {
            System.out.println("No previous products");
        }
    }

    public void readStore() {
        try (BufferedReader br = new BufferedReader(new FileReader("StoreDatabase.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split("\\|");
                String userUsername = productData[0];
                String storeName = productData[1];
                User tempUser = null;
                ArrayList<Product> tempStoreProducts = null;
                for (Product product : products) {
                    if (storeName.equals(product.getProductStoreName())) {
                        tempStoreProducts.add(product);
                    }
                }
                for (User user : users) {
                    if (userUsername.equals(user.getUsername())) {
                        tempUser = user;
                    }
                }
                Store store = new Store(storeName, tempUser);
                Seller currentSeller = (Seller) currentUser;
                currentSeller.createYourStore(store);
                this.getStores().add(store);
                System.out.println("store name: " + store.getStoreName());
                System.out.println("Stores Loaded");
            }
        } catch (IOException e) {
            System.out.println("No previous stores");
        }
    }

    public void readUser() {
        try (BufferedReader br = new BufferedReader(new FileReader("UserDatabase.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split("\\|");
                String email = productData[0];
                String password = productData[1];
                String username = productData[2];
                boolean seller = Boolean.parseBoolean(productData[3]);
                User user;
                if (seller) {
                    user = new Seller(email, password, username);
                    currentUser = user;

                } else
                    user = new Customer(email, password, username);
                this.getUsers().add(user);
                System.out.println("Users Loaded");
            }
        } catch (IOException e) {
            System.out.println("No previous users");
        }
    }

    private void writeUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("UserDatabase.txt"))) {
            String fileUser = "";
            for (User u : users) {
                fileUser = u.getEmail() + "|" + u.getPassword() + "|" + u.getUsername() + "|" + u.isSeller() + "\n";
                bw.write(fileUser);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStore() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("StoreDatabase.txt"))) {
            for (Store s : stores) {
                String fileStore = s.getOwner().getUsername() + "|" + s.getStoreName() + "\n";
                bw.write(fileStore);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeProduct() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ProductDatabase.txt", false))) {
            for (Product p : products) {
                String fileProduct = p.getProductName() + "|" + p.getDescription() + "|" + p.getQuantity() + "|" +
                        p.getPrice() + "|" + p.getProductStoreName() + "\n";
                bw.write(fileProduct);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        writeUsers();
        writeProduct();
        writeStore();
    }

    public boolean create(String email, String username, String password, boolean seller) {

        if (!email.contains("@") || !email.contains(".") || email.contains("|")) {
            System.out.println("Email not valid. Please try again or login");
            return false;
        }
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Email already in use. Login or try a different email");
                return false;
            }
        }

        if (username.contains("|")) {
            System.out.println("Username cannot contain a '|' symbol");
            return false;
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already in use!!!");
                return false;

            }
        }

        if (password.contains("|")) {
            System.out.println("Password cannot contain a '|' symbol");
            return false;
        }
        System.out.println("Valid password");


        if (seller) {
            currentUser = new Seller(email, password, username);

            this.getUsers().add(new Seller(email, password, username));


        } else {
            currentUser = new Customer(email, password, username);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("UserDatabase.txt"))) {
                String fileUser = email + "|" + password + "|" + username + "|false\n";
                bw.write(fileUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.getUsers().add(new Customer(email, password, username));
        }
        return true;
    }

    public String login(String username, String password) {
        User loginUser = null;
        boolean valid = false;
        if (users.isEmpty()) {
            System.out.println("No previous users");
            return "failure";
        } else {
            for (User user : users) {
                if (user.getUsername().equals(username) || user.getEmail().equals(username)) {
                    System.out.println("Account found");
                    loginUser = user;
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                System.out.println("Invalid Username or Email");
                return "failure";
            }
            if (loginUser.getPassword().equals(password)) {
                System.out.println("Logged In");
                currentUser = loginUser;
                if (currentUser.isSeller())
                    return "seller";
                else
                    return "customer";
            } else {
                System.out.println("Wrong password. Please retry");
                return "failure";
            }
        }
    }


    public boolean sortQuantity() {
        Comparator<Product> quantityComparator = Comparator.comparingInt(Product::getQuantity);
        if (this.getProducts() == null) {
            return false;
        } else {
            List<Product> products = this.getProducts();
            products.sort(quantityComparator);
        }
        return true;
    }

    public boolean sortPrice() {
        Comparator<Product> priceComparator = Comparator.comparingDouble(Product::getPrice);
        if (this.getProducts() == null) {
            return false;
        } else {
            List<Product> products = this.getProducts();
            products.sort(priceComparator);
        }
        return true;
    }


    public void createStore(String storeName) {
        Store store = new Store(storeName, currentUser);
        Seller currentSeller = (Seller) currentUser;
        currentSeller.createYourStore(store);
        this.stores.add(store);
    }

    public boolean editProduct(String storeName, String productName, String newproductName, String desc, int quantity, double price) {
        boolean found = false;
        Store editStore = null;
        Product editProduct = null;
        Seller seller = (Seller) currentUser;
        for (Store wantedStore : seller.getYourStores()) {
            if (wantedStore.getStoreName().equals(storeName)) {
                editStore = wantedStore;
                found = true;
            }
        }
        if (!found) {
            return false;
        }
        found = false;
        for (Product wantedProduct : editStore.getProducts()) {
            if (wantedProduct.getProductName().equals(productName)) {
                editProduct = wantedProduct;
                found = true;
                break;

            }
        }
        if (!found) {

            return false;
        }

        Seller.editProduct(editProduct, editStore, newproductName,
                desc, quantity, price);

        return true;
    }

    public String customerStatistics() {
        StringBuilder result = new StringBuilder();
        Seller seller = (Seller) currentUser;
        for (Store aStore : seller.getYourStores()) {
            result.append(aStore.getStoreName()).append(":");
            for (Customer aCustomer : aStore.getPurchases()) {
                result.append(aCustomer.getPurchaseCount()).append("$");
            }
            result.append("All Customer Statistics");
            break;
        }
        return result.toString();
    }

    public String productStatistics() {
        StringBuilder result = new StringBuilder();
        Seller seller = (Seller) currentUser;
        for (Store aStore : seller.getYourStores()) {
            result.append(aStore.getStoreName()).append(":");
            for (Product aProduct : aStore.getProducts()) {
                result.append(aProduct.getProductName()).append(":").append(aProduct.getSales()).append("$");
            }
        }
        result.append("All Product Statistics");

        return result.toString();
    }

    public boolean removeProduct(String storeName, String productName) {
        boolean found = false;
        Store removeStore = null;
        Product removeProduct = null;
        Seller seller = (Seller) currentUser;
        for (Store wantedStore : seller.getYourStores()) {
            if (wantedStore.getStoreName().equals(storeName)) {
                removeStore = wantedStore;
                found = true;
                break;
            }
        }
        if (!found) {

            return false;
        }
        for (Product wantedProduct : removeStore.getProducts()) {
            if (wantedProduct.getProductName().equals(productName)) {
                removeProduct = wantedProduct;
            }
        }
        Seller.removeProduct(removeProduct, removeStore);

        return true;
    }

    public boolean addProduct(String storeName, String productName, String description, int quantity, double price) throws IOException {
        Product product = new Product(productName, description,
                quantity, price, storeName);
        Seller seller = (Seller) currentUser;
        for (Store wantedStore : seller.getYourStores()) {
            if (wantedStore.getStoreName().equals(storeName)) {
                wantedStore.getProducts().add(product);
                this.products.add(product);
                return true;
            }
        }
        return false;

    }


    public boolean addProductToCart(String ProductName) {
        Customer currentCustomer = (Customer) currentUser;
        for (Product product : this.products) {
            if (product.getProductName().toLowerCase().
                    contains(ProductName.toLowerCase())) {
                currentCustomer.addToShoppingCart(product);
                return true;
            }
        }
        return false;
    }

    public boolean buyProduct(String ProductName, int quantity) {
        Customer currentCustomer = (Customer) currentUser;
        for (Product product : this.products) {
            if (product.getProductName().toLowerCase().
                    contains(ProductName.toLowerCase())) {
                return currentCustomer.purchaseProduct(this, product, quantity);

            }

        }
        return false;
    }

    public String search(String productName) {
        Customer currentCustomer = (Customer) currentUser;
        StringBuilder result = new StringBuilder();
        ArrayList<Product> matchingProducts = currentCustomer.searchProducts(products, productName);
        for (Product product : matchingProducts) {
            result.append(product.getProductStoreName()).append(":");
            result.append(product.getProductName()).append(":");
            result.append(product.getPrice()).append(":");
            result.append("$");
        }
        result.append("All matching products");
        return result.toString();
    }

    public String viewPurchases() {
        Customer currentCustomer = (Customer) currentUser;
        StringBuilder result = new StringBuilder();
        for (Product product : currentCustomer.getPurchasedItems()) {
            result.append(product.getProductStoreName()).append(":");
            result.append(product.getProductName()).append(":");
            result.append(product.getPrice()).append(":");
            result.append("$");
        }
        result.append("All purchases");
        return result.toString();
    }

    public String view() {
        StringBuilder result = new StringBuilder();
        for (Product product : this.products) {
            result.append(product.getProductStoreName()).append(":");
            result.append(product.getProductName()).append(":");
            result.append(product.getPrice()).append(":");
            result.append("$");
        }
        result.append("All products");
        return result.toString();
    }


}

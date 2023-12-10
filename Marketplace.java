import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Marketplace {

    private static ArrayList<Product> products;
    private static ArrayList<Store> stores;
    private static ArrayList<User> users;
    private static User currentUser;

    public static void declare()
    {
        products = new ArrayList<>();
        stores = new ArrayList<>();
        users = new ArrayList<>();
    }
    public static void setProducts(ArrayList<Product> products) {
        Marketplace.products = products;
    }

    public static void setStores(ArrayList<Store> stores) {
        Marketplace.stores = stores;
    }

    public static void setUsers(ArrayList<User> users) {
        Marketplace.users = users;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<Store> getStores() {
        return stores;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void main(String[] args) {
        declare();
        Scanner s = new Scanner(System.in);
        boolean isRunning = true;
        System.out.println("Loading Servers...");
        try (BufferedReader br = new BufferedReader(new FileReader("UserDatabase.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split(",");
                String email = productData[0];
                String password = productData[1];
                String username = productData[2];
                boolean seller = Boolean.parseBoolean(productData[3]);
                User user = new User(email, password, username, seller);
                Marketplace.getUsers().add(user);
                System.out.println("Users Loaded");
            }
        } catch (IOException e) {
            System.out.println(" No previous users");
        }
        readProduct();
        readStore();
        while (isRunning) {
            if (currentUser == null) {
                System.out.println("Welcome to the Marketplace!");
                System.out.println("1. Login");
                System.out.println("2. Create Account");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = s.nextInt();
                s.nextLine();
                switch (choice) {
                    case 1 -> login(s);
                    case 2 -> create(s);
                    case 3 -> {
                        isRunning = false;
                        System.out.println("Exiting the Marketplace. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } else {
                if (currentUser.isSeller()) {
                    sellerMenu(s);
                    currentUser = null;
                }
                else {
                    customerMenu(s);
                    currentUser = null;
                }

            }
        }

    }

    public static void readProduct() {
        try (BufferedReader br = new BufferedReader(new FileReader("ProductDatabase.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split(",");
                String name = productData[0];
                String description = productData[1];
                int quantity = Integer.parseInt(productData[2]);
                double price = Double.parseDouble(productData[3]);
                String storeName = productData[4];
                Product product = new Product(name, description, quantity, price, storeName);
                Marketplace.getProducts().add(product);
                System.out.println("Products Loaded");
            }
        } catch (IOException e) {
            System.out.println("No previous products");
        }
    }

    public static void readStore() {
        try (BufferedReader br = new BufferedReader(new FileReader("StoreDatabase.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split(",");
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
                Store store = new Store(tempStoreProducts, storeName, tempUser);
                Marketplace.getStores().add(store);
                System.out.println("Stores Loaded");
            }
        } catch (IOException e) {
            System.out.println("No previous stores");
        }
    }


    private static void create(Scanner s) {
        String email = "";
        String password = "";
        String username = "";
        String role = "";
        boolean seller = true;
        boolean valid = true;
        boolean validTwo = true;
        boolean validThree = true;
        System.out.println("Enter email");
        email = s.nextLine();
        if (!email.contains("@") || !email.contains(".") || email.contains(",")) {
            System.out.println("Email not valid. Please try again or login");
            return;
        }

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Email already in use. Login or try a different email");
                return;
            }
        }
        while (valid) {
            System.out.println("Please enter  username");
            username = s.nextLine();
            if (username.contains(",")) {
                System.out.println("Username cannot contain a ',' symbol");
                continue;
            }
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    System.out.println("Username already in use!!!");
                    valid = false;
                    break;
                }
            }
            valid = !valid;

        }
        while (validTwo) {
            System.out.println("Please enter desired password");
            password = s.nextLine();
            if (password.contains(",")) {
                System.out.println("Password cannot contain a ',' symbol");
                continue;
            }
            System.out.println("Valid password");
            validTwo = false;
        }
        while (validThree) {
            System.out.println("Please enter desired role:Seller/Customer");
            role = s.nextLine();
            if (role.equalsIgnoreCase("customer")) {
                seller = false;
                validThree = false;
            } else if (role.equalsIgnoreCase("seller")) {
                validThree = false;
            } else {
                System.out.println("Enter valid role");
            }
        }

        if (seller) {
            currentUser = new Seller(email, password, username);
            Marketplace.getUsers().add(new Seller(email, password, username));

        }
        else {
            currentUser = new Customer(email, password, username);
            Marketplace.getUsers().add(new Customer(email, password, username));
        }
    }

    private static void login(Scanner s) {
        User loginUser = null;
        boolean valid = true;
        boolean validTwo = true;
        while (valid) {
            System.out.println("Enter username or email");
            String account = s.nextLine();
            for (User user : users) {
                if (user.getUsername().equals(account) || user.getEmail().equals(account)) {
                    System.out.println("Account found");
                    loginUser = user;
                    valid = false;
                    break;
                }
            }
            if (valid)
                System.out.println("Invalid Username or Email");
        }
        while (validTwo) {
            System.out.println("Enter password");
            String password = s.nextLine();
            if (loginUser.getPassword().equals(password)) {
                System.out.println("Logged In");
                currentUser = loginUser;
                validTwo = false;
            } else {
                System.out.println("Wrong password. Please retry");
            }
        }
    }



    public void sortQuantity() {
        Comparator<Product> quantityComparator = Comparator.comparingInt(Product::getQuantity);
        if (Marketplace.getProducts() == null) {
            System.out.println("No products to sort");
        } else {
            Collections.sort(Marketplace.getProducts(), quantityComparator);
        }
    }

    public void sortPrice() {
        Comparator<Product> priceComparator = Comparator.comparingDouble(Product::getPrice);
        if (Marketplace.getProducts() == null) {
            System.out.println("No products to sort");
        } else {
            Collections.sort(Marketplace.getProducts(), priceComparator);
        }
    }

    public static void displayStores(Seller currentSeller)
    {
        System.out.println("Enter store name from the available stores");
        for (Store st :currentSeller.getStores()) {
            System.out.println(st.getStoreName());
        }
    }
    public static void sellerMenu(Scanner s) {
        Seller currentSeller = (Seller) currentUser;
        String storeName = "";
        String productName = "";
        String productDescription = "";
        int productquantity = 0;
        double productPrice = 0;
        System.out.println("Seller Menu:");
        System.out.println("1. Create Store");
        System.out.println("2. Add Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Edit Product");
        System.out.println("5. View Sales");
        System.out.println("6. View Statistics");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();
        s.nextLine();
        switch (choice) {
            case 1:
                System.out.println("enter desired store name");
                storeName = s.nextLine();
                System.out.println("Please enter the name of first product for store");
                productName = s.nextLine();
                System.out.println("Please enter the description of first product for store");
                productDescription = s.nextLine();
                System.out.println("Please enter the quantity of first product for store");
                productquantity = s.nextInt();
                s.nextLine();
                System.out.println("Please enter the price of first product for store");
                productPrice = s.nextDouble();
                s.nextLine();
                Product firstProduct = new Product(productName, productDescription,
                        productquantity, productPrice, storeName);
                Marketplace.products.add(firstProduct);
                ArrayList<Product> storeProducts = new ArrayList<Product>();
                storeProducts.add(firstProduct);
                Store store = new Store(storeProducts, storeName, currentUser);
                currentSeller.createYourStore(store);
                System.out.println("Store and first product created");
                break;
            case 2:
                displayStores(currentSeller);
                storeName = s.nextLine();
                System.out.println("Please enter the name of product for store");
                productName = s.nextLine();
                System.out.println("Please enter the description of product for store");
                productDescription = s.nextLine();
                System.out.println("Please enter the quantity of product for store");
                productquantity = s.nextInt();
                s.nextLine();
                System.out.println("Please enter the price of product for store");
                productPrice = s.nextDouble();
                s.nextLine();
                Product product = new Product(productName, productDescription,
                        productquantity, productPrice, storeName);
                Marketplace.products.add(product);
                for (Store wantedStore : Seller.getYourStores()) {
                    if (wantedStore.getStoreName().equals(storeName)) {
                        wantedStore.getProducts().add(product);
                    }
                }
                System.out.println("Product added to desired store ");
                break;
            case 3:
                Store removeStore = null;
                Product removeProduct = null;
                displayStores(currentSeller);
                storeName = s.nextLine();
                System.out.println("Enter name of product to remove");
                productName = s.nextLine();
                boolean found = false;
                for (Store wantedStore : Seller.getYourStores()) {
                    if (wantedStore.getStoreName().equals(storeName)) {
                        removeStore = wantedStore;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Sorry!! Store name mismatch");
                    break;
                }
                for (Product wantedProduct : removeStore.getProducts()) {
                    if (wantedProduct.getProductName().equals(productName)) {
                        removeProduct = wantedProduct;
                    }
                }
                Seller.removeProduct(removeProduct, removeStore);
                System.out.println("Product removed");
                break;
            case 4:
                Store editStore = null;
                Product editProduct = null;

                displayStores(currentSeller);
                storeName = s.nextLine();
                System.out.println("Enter name of product to remove");
                productName = s.nextLine();
                found = false;
                for (Store wantedStore : Seller.getYourStores()) {
                    if (wantedStore.getStoreName().equals(storeName)) {
                        editStore = wantedStore;
                        found = true;
                    }

                }
                if (!found)
                {
                    System.out.println("Sorry!! wrong store name");
                    break;
                }
                found = false;
                for (Product wantedProduct : editStore.getProducts()) {
                    if (wantedProduct.getProductName().equals(productName)) {
                        editProduct = wantedProduct;
                        found = true;
                        break;

                    }
                }
                if(!found)
                {
                    System.out.println("sorry!!! product not found");
                    break;
                }
                System.out.println("Please enter the new name of product for store");
                productName = s.nextLine();
                System.out.println("Please enter the new description of product for store");
                productDescription = s.nextLine();
                System.out.println("Please enter the new quantity of product for store");
                productquantity = s.nextInt();
                s.nextLine();
                System.out.println("Please enter the new price of product for store");
                productPrice = s.nextDouble();
                s.nextLine();
                Seller.editProduct(editProduct, editStore, productName,
                        productDescription, productquantity, productPrice);
                System.out.println("Product edited");
                break;
            case 5:
                for (Store aStore : Seller.getYourStores()) {
                    System.out.println("store name: " + aStore.getStoreName());
                    for (Customer aCustomer : aStore.getPurchases()) {
                        System.out.println("\n Purchase By: " + aCustomer.getUsername());
                        for (Product aProduct : aCustomer.getPurchasedItems()) {
                            double revenue = (aCustomer.getPurchaseCount() * aProduct.getPrice());
                            System.out.println("\nMoney made: " + revenue);
                        }
                    }
                }
                System.out.println("All sales");
                break;
            case 6:
                System.out.println("1. Customer Statistics");
                System.out.println("2. Product Statistics");
                int statisticChoice = s.nextInt();
                s.nextLine();
                switch (statisticChoice) {
                    case 1:
                        for (Store aStore : Seller.getYourStores()) {
                            System.out.println("Store name: " + aStore.getStoreName());
                            for (Customer aCustomer : aStore.getPurchases()) {
                                System.out.println("\n Purchase Amount: " + aCustomer.getPurchaseCount());
                            }
                            System.out.println("All Customer Statistics");
                            break;
                        }
                    case 2:
                        for (Store aStore : Seller.getYourStores()) {
                            System.out.println("Store name: " + aStore.getStoreName());
                            for (Product aProduct : aStore.getProducts()) {
                                System.out.println("\nProduct Name: " + aProduct.getProductName()
                                        + "\nProduct Sales: " + aProduct.getSales());
                            }
                        }
                        System.out.println("All Product Statistics");
                        break;
                }
                break;


            case 7:
                System.out.println("Logged out successfully!");
                return;

            default:
                System.out.println("Enter a number 1 through 8");
                break;
        }
    }

    public static void customerMenu(Scanner s) {
        Customer currentCustomer = (Customer) currentUser;
        System.out.println("Customer Menu:");
        System.out.println("1. View Marketplace");
        System.out.println("2. Select Product");
        System.out.println("3. Sort Marketplace");
        System.out.println("4. Search Marketplace");
        System.out.println("5. View Shopping Cart");
        System.out.println("6. Statistics");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();
        s.nextLine();
        switch (choice) {
            case 1:
                for (Product product : Marketplace.products) {
                    System.out.println("Store: " + product.getProductStoreName());
                    System.out.println("Product: " + product.getProductName());
                    System.out.println("Price: " + product.getPrice());
                    System.out.println("Quantity: " + product.getQuantity());
                    System.out.println("------------------");
                }
                System.out.println("All products");
                break;
            case 2:
                System.out.println("Enter desired Product name");
                String desiredProductName = s.nextLine();
                for (Product product : Marketplace.products) {
                    if (product.getProductName().toLowerCase().
                            contains(desiredProductName.toLowerCase())) {
                        System.out.println("Product Selected");
                        System.out.println("1: Buy Product");
                        System.out.println("2. Add Product to Cart");
                        int productChoice = s.nextInt();
                        s.nextLine();
                        switch (productChoice) {
                            case 1:
                                System.out.println("Enter desired purchase Quantity");
                                int desiredAmount = s.nextInt();
                                s.nextLine();
                                currentCustomer.purchaseProduct(product, desiredAmount);
                                break;
                            case 2:
                                currentCustomer.addToShoppingCart(product);
                                break;
                            default:
                                System.out.println("Please enter 1 or 2");
                                break;
                        }
                    }
                }
            case 3:
                System.out.println("Sort by quantity or price?");
                System.out.println("1. Quantity");
                System.out.println("2. Price");
                int sortChoice = s.nextInt();
                s.nextLine();

                switch (sortChoice) {
                    case 1:

                        break;
                    case 2:

                        break;
                    default:
                        System.out.println("Please enter 1 or 2");
                        break;
                }
            case 4:
                System.out.println("Enter search term");
                String searchTerm = s.nextLine();
                ArrayList<Product> matchingProducts = currentCustomer.searchProducts(products, searchTerm);
                for (Product product : matchingProducts) {
                    System.out.println("Store: " + product.getProductStoreName());
                    System.out.println("Product: " + product.getProductName());
                    System.out.println("Price: " + product.getPrice());
                    System.out.println("Quantity: " + product.getQuantity());
                    System.out.println("------------------");
                }
                System.out.println("All matching products");
                break;
            case 5:
                for (Product product : currentCustomer.getShoppingCart()) {
                    System.out.println("Store: " + product.getProductStoreName());
                    System.out.println("Product: " + product.getProductName());
                    System.out.println("Price: " + product.getPrice());
                    System.out.println("Quantity: " + product.getQuantity());
                    System.out.println("------------------");
                }
                System.out.println("All matching products");
                break;
            case 6:
                System.out.println("Get personal purchases or total purchases");
                System.out.println("1. Personal");
                System.out.println("2. Total");
                int statisticChoice = s.nextInt();
                s.nextLine();
                switch (statisticChoice) {
                    case 1:
                        for (Product product : currentCustomer.getPurchasedItems()) {
                            System.out.println("Store: " + product.getProductStoreName());
                            System.out.println("Product: " + product.getProductName());
                            System.out.println("Price: " + product.getPrice());
                            System.out.println("Quantity: " + product.getQuantity());
                            System.out.println("------------------");
                        }
                        break;
                    case 2:
                        for (Store store: Marketplace.getStores()) {
                            System.out.println("Store: " + store.getStoreName());
                            System.out.println("Purchases: " + store.getPurchases().size());
                            System.out.println("------------------");
                        }
                        break;
                    default:
                        System.out.println("Enter 1 or 2");
                        break;
                }
            case 7:
                System.out.println("Logged out successfully");
                break;
            default:
                System.out.println("Please enter number 1 through 7");
                break;
        }
    }

}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Marketplace {

    private static ArrayList<Product> products;
    private static ArrayList<Store> stores;
    private static ArrayList<User> users;
    private static User currentUser;



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
            System.out.println("No previous users");
            while (isRunning) {
                System.out.println("Welcome to the Marketplace!");
                System.out.println("1. Create Account");
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");
                int choice = s.nextInt();
                s.nextLine();
                switch (choice) {
                    case 1:
                        create(s);
                        break;
                    case 2:
                        isRunning = false;
                        System.out.println("Exiting the Marketplace. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }
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
                    case 1:
                        login(s);
                        break;
                    case 2:
                        create(s);
                        break;
                    case 3:
                        isRunning = false;
                        System.out.println("Exiting the Marketplace. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            else {
                if (currentUser.isSeller()) {

                } else if (!currentUser.isSeller()) {

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
        for (User user:users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Email already in use. Login or try a different email");
                return;
            } else if (!email.contains("@") || !email.contains(".") || email.contains(",")) {
                System.out.println("Email not valid. Please try again or login");
                return;
            }
        }
        while (valid) {
            System.out.println("Please enter desired username");
            username = s.nextLine();
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    System.out.println("Username already in use reenter username;");
                } else if (username.contains(",")) {
                    System.out.println("Username cannot contain a ',' symbol");
                } else {
                    System.out.println("Valid username");
                    valid = false;
                }
            }
        }
        while (validTwo) {
            System.out.println("Please enter desired password");
            password = s.nextLine();
            if (password.contains(",")) {
                System.out.println("Password cannot contain a ',' symbol");
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
        Marketplace.getUsers().add(new User(email,password,username,seller));
        currentUser = new User(email,password,username,seller);
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
                } else {
                    System.out.println("No account found try again");
                }
            }
        }
        while(validTwo) {
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
    public static void sellerMenu(Scanner s) {
        System.out.println("Seller Menu:");
        System.out.println("1. Create Store");
        System.out.println("2. Add Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Edit Product");
        System.out.println("5. View Sales");
        System.out.println("6. View Statistics");
        System.out.println("7. Shopping Cart");
        System.out.println("8. Logout");
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();
        s.nextLine();
    }
    public static void customerMenu(Scanner s) {
        System.out.println("Customer Menu:");
        System.out.println("1. View Marketplace");
        System.out.println("2. Sort Marketplace");
        System.out.println("3. Search Marketplace");
        System.out.println("4. View Shopping Cart");
        System.out.println("5. Statistics");
        System.out.println("6. Logout");
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();
        s.nextLine();
    }

}

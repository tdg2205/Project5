import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private static final Marketplace marketplace = new Marketplace();

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);


            while (true) {
                String command = reader.readLine();
                if (command == null || command.equals("exit")) {
                    break;
                }


                String response = processCommand(command);
                marketplace.write();
                writer.println(response);
            }

            System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String processCommand(String command) throws IOException {
        String[] commandParts = command.split("\\s+");
        String result = "";
        boolean out;
        switch (commandParts[0]) {
            case "login":
                String r = login(commandParts[1], commandParts[2]);
                if(r.equals("seller"))
                    result = "seller";
                else if(r.equals("customer"))
                    result="customer";
                else if(r.equals("failure"))
                    result="failure";
                break;
            case "create":
                boolean seller = false;
                if (commandParts[4].equals("seller"))
                    seller=true;
                out = createUser(commandParts[1], commandParts[2], commandParts[3], seller);
                if (out)
                    if(seller)
                        result = "seller";
                    else
                        result="customer";
                else
                    result="failure";
                break;

            case "createstore":
                createStore(commandParts[1]);
                result = "Store created successfully!";
                break;
            case "addproduct":
                out = addProduct(commandParts[1], commandParts[2], commandParts[3], Integer.parseInt(commandParts[4]),
                        Double.parseDouble(commandParts[5]));
                if(out)
                    result = "Product added successfully!";
                else
                    result = "Product not added";
                break;
            case "removeproduct":
                removeProduct(commandParts[1], commandParts[2]);
                result = "Product removed successfully!";
                break;
            case "edit":
                out = editProduct(commandParts[1], commandParts[2], commandParts[3], commandParts[4], Integer.parseInt(commandParts[5]),
                        Double.parseDouble(commandParts[6]));
                if (out)
                    result = "Product edited successfully!";
                else
                    result = "Product not found";
                break;
            case "viewsales":
                result = viewSales();
                break;
            case "statistics":
                if (commandParts[1].equalsIgnoreCase("customer")) {
                    result = customerStatistics();
                } else if (commandParts[1].equalsIgnoreCase("product")) {
                    result = productStatistics();
                } else {
                    result = "Invalid statistics command.";
                }
                break;
            case "view":
                result = marketplace.view();
                break;
            case "product":
                if (commandParts.length >= 2) {
                    switch (commandParts[2]) {

                        case "buy":
                            out = buyProduct(commandParts[1], Integer.parseInt(commandParts[3]));
                            if (out)
                                result = "Product bought successfully!";
                            else
                                result = "product/quantity not available";
                            break;
                        case "add":
                            out =  addProductToCart(commandParts[1]);
                            if (out)
                                result = "Product added to cart successfully!";
                            else
                                result = "product/quantity not available";
                            break;
                        default:
                            result = "Invalid product command.";
                    }
                } else {
                    result = "Invalid product command.";
                }
                break;
            case "sort":
                if (commandParts.length > 1) {
                    switch (commandParts[1]) {
                        case "quantity":
                            out = marketplace.sortQuantity();
                            if(out)
                                result = "Products sorted by quantity.";
                            else
                                result = "No product is added";

                            break;
                        case "price":
                            out = marketplace.sortPrice();
                            if (out)
                                result = "Products sorted by price.";
                            else
                                result = "No product is added";
                            break;
                        default:
                            result = "Invalid sort command.";
                    }
                } else {
                    result = "Invalid sort command.";
                }
                break;
            case "search":
                if (commandParts.length > 1) {
                    result = search(commandParts[1]);
                } else {
                    result = "Invalid search command.";
                }
                break;
            case "cart":
                result = viewShoppingCart();
                break;
            case "purchases":
                result = marketplace.viewPurchases();
                break;
            case "exit":
                result = "Goodbye!";
                break;
            default:
                result = "Invalid command.";
        }

        return result;
    }

    private String search(String productName) {
        return marketplace.search(productName);
    }

    private boolean addProductToCart(String ProductName) {
        return marketplace.addProductToCart(ProductName);
    }

    private boolean buyProduct(String ProductName, int i) {
        return marketplace.buyProduct( ProductName, i);
    }

    private boolean editProduct(String storeName, String productName, String newproductName,String desc ,int quantity, double price)
    {

        return marketplace.editProduct(storeName,  productName,  newproductName, desc , quantity,  price);
    }

    private boolean removeProduct(String storename,String product)
    {
        return marketplace.removeProduct(storename,product);
    }
    private void createStore(String storeName) {

        marketplace.createStore(storeName);
    }


    private boolean addProduct(String storeName,String productName, String description, int quantity, double price) throws IOException {

        return marketplace.addProduct(storeName,productName, description, quantity, price);
    }

    private  String productStatistics()
    {
        return  marketplace.productStatistics();
    }
    private String customerStatistics()
    {

        return marketplace.customerStatistics();
    }
    private String viewSales() {

        StringBuilder result = new StringBuilder();
        for (Store store : marketplace.getStores()) {
            result.append("Store: ").append(store.getStoreName()).append("$");
            for (Customer customer : store.getPurchases()) {
                result.append("Purchase By: ").append(customer.getUsername()).append("$");
                for (Product product : customer.getPurchasedItems()) {
                    double revenue = customer.getPurchaseCount() * product.getPrice();
                    result.append("Money made: ").append(revenue).append("$");
                }
            }
        }
        result.append("$All sales");
        return result.toString();
    }

    private String viewMarketplace() {
        StringBuilder result = new StringBuilder();
        for (Product product : marketplace.getProducts()) {
            result.append("Store: ").append(product.getProductStoreName()).append("$");
            result.append("Product: ").append(product.getProductName()).append("$");
            result.append("Price: ").append(product.getPrice()).append("$");
            result.append("Quantity: ").append(product.getQuantity()).append("$");
            result.append("------------------").append("$");
        }
        result.append("All products");
        return result.toString();
    }

    private void selectProduct(String desiredProductName) {

        for (Product product : marketplace.getProducts()) {
            if (product.getProductName().toLowerCase().contains(desiredProductName.toLowerCase())) {

                System.out.println("Product Selected");
                break;
            }
        }
    }

    private String viewShoppingCart() {

        StringBuilder result = new StringBuilder();
        for (Product product : getUserShoppingCart()) {
            result.append("Store: ").append(product.getProductStoreName()).append("$");
            result.append("Product: ").append(product.getProductName()).append("$");
            result.append("Price: ").append(product.getPrice()).append("$");
            result.append("Quantity: ").append(product.getQuantity()).append("$");
            result.append("------------------").append("$");
        }
        result.append("All matching products");
        return result.toString();
    }

    private User getUserByUsername(String username) {
        for (User user : marketplace.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private Store getStoreByName(String storeName) {

        for (Store store : marketplace.getStores()) {
            if (store.getStoreName().equals(storeName)) {
                return store;
            }
        }
        return null;
    }

    private ArrayList<Product> getUserShoppingCart() {
        Customer currentCustomer = (Customer) marketplace.getCurrentUser();

        return currentCustomer.getShoppingCart();
    }


    private boolean createUser(String email, String username, String password, boolean seller) {

        return marketplace.create( email,  username, password, seller);
    }

    private String login(String username, String password) {
        return marketplace.login(username,password);
    }
}

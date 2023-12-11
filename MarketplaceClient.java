import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Store.java
 * <p>
 * This is a Store.java class which contains all the fields, getters and setters method along with increasing purchase count and updating quanity methods.
 *
 * @author Krish Sharma and Tyler Gentry, lab sec 30
 * @version 11 November, 2023
 */
class Helper {
    public String readCustomer() {
        String input = "";
        String[] menuOption = {"View Marketplace", "Select Product", "Sort Marketplace", "Search Marketplace", "View Shopping Cart", "View Purchases", "Logout"};
        String[] sortOption = {"Quantity", "Price"};
        String[] selectOption = {"Buy Product", "Add to Cart"};
        String choice = (String) JOptionPane.showInputDialog(null, "Customer Menu", "Marketplace",
                JOptionPane.PLAIN_MESSAGE, null, menuOption, null);
        switch (choice) {
            case "View Marketplace":
                input = "view";
                break;
            case "Select Product":
                System.out.println("Enter desired Product name");
                input = "product ";
                String desiredProductName = JOptionPane.showInputDialog(null, "Enter product name", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                input += desiredProductName + " ";
                String productChoice = (String) JOptionPane.showInputDialog(null, "Quantity or Price Sort?", "Marketplace",
                        JOptionPane.PLAIN_MESSAGE, null, selectOption, null);
                switch (productChoice) {
                    case "Buy Product":
                        int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter quantity of product to purchase", "Marketplace",
                                JOptionPane.QUESTION_MESSAGE));
                        input += "buy " + quantity;
                        break;
                    case "Add to Cart":
                        input += "add ";
                        break;
                    default:
                        System.out.println("Please enter 1 or 2");
                        break;
                }
                break;

            case "Sort Marketplace":
                String sortChoice = (String) JOptionPane.showInputDialog(null, "Quantity or Price Sort?", "Marketplace",
                        JOptionPane.PLAIN_MESSAGE, null, sortOption, null);
                //not working cant figure out why
                switch (sortChoice) {
                    case "Quantity":
                        input = "sort quantity";

                        break;
                    case "Price":
                        input = "sort price";
                        break;
                    default:
                        System.out.println("Please enter 1 or 2");
                        break;
                }
                break;
            case "Search Marketplace":
                String searchTerm = JOptionPane.showInputDialog(null, "Enter search term", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                input = "search " + searchTerm;
                break;
            case "View Shopping Cart":
                input = "cart";
                break;
            case "View Purchases":
                input = "purchases";
                break;
            case "Logout":
                input = "exit";
                break;
            default:
                System.out.println("Please enter number 1 through 7");
                break;
        }
        return input;
    }

    public String readSeller() {
        String input = "";
        Scanner s = new Scanner(System.in);
        String storeName = "";
        String productName = "";
        String productDescription = "";
        String productDescriptionFirst = "";
        int productquantity = 0;
        double productPrice = 0;
        String[] menuOption = {"Create Store", "Add Product", "Remove Product", "Edit Product",
                "View Sales", "View Statistics", "Logout"};
        String[] statOption = {"Customer Statistics", "Product Statistics"};
        String choice = (String) JOptionPane.showInputDialog(null, "Seller Menu", "Marketplace",
                JOptionPane.PLAIN_MESSAGE, null, menuOption, null);
        switch (choice) {
            case "Create Store":
                input = "createstore ";
                storeName = JOptionPane.showInputDialog(null, "Enter store name", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                input += storeName;
                break;
            case "Add Product":
                input = "addproduct ";
                storeName = JOptionPane.showInputDialog(null, "Enter store name", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                productName = JOptionPane.showInputDialog(null, "Enter product name", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                productDescriptionFirst = JOptionPane.showInputDialog(null, "Enter product description", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                productDescription = productDescriptionFirst.replace(" ", "-");
                productquantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter product quantity", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE));
                productPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter product price", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE));
                input += storeName + " " + productName + " " + productDescription + " " + productquantity + " " + productPrice;
                break;
            case "Remove Product":
                input = "removeproduct ";
                storeName = JOptionPane.showInputDialog(null, "Enter store name", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                productName = JOptionPane.showInputDialog(null, "Enter product name", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                input += storeName + " " + productName;
                break;
            case "Edit Product":
                input = "edit ";
                storeName = JOptionPane.showInputDialog(null, "Enter store name", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                productName = JOptionPane.showInputDialog(null, "Enter product name", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                String productnewName = JOptionPane.showInputDialog(null, "Enter product new name", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                productDescriptionFirst = JOptionPane.showInputDialog(null, "Enter new product description", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE);
                productDescription = productDescriptionFirst.replace(" ", "-");
                productquantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new product quantity", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE));
                productPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter new product price", "Marketplace",
                        JOptionPane.QUESTION_MESSAGE));
                input += storeName + " " + productName + " " + productnewName + " " + productDescription + " " + productquantity + " " + productPrice;
                break;
            case "View Sales":
                input = "viewsales";
                break;
            case "View Statistics":
                System.out.println("1. Customer Statistics");
                System.out.println("2. Product Statistics");
                String statisticChoice = (String) JOptionPane.showInputDialog(null, "Customer or Product Statistics?", "Marketplace",
                        JOptionPane.PLAIN_MESSAGE, null, statOption, null);
                s.nextLine();
                input = switch (statisticChoice) {
                    case "Customer Statistics" -> "statistics customer";
                    case "Product Statistics" -> "statistics product";
                    default -> input;
                };
                break;

            case "Logout":
                input = "exit";
            default:
                System.out.println("Enter a number 1 through 8");

        }
        return input;
    }

    public String readLogin() {
        String input = "login ";
        String username = JOptionPane.showInputDialog(null, "Enter your  or username", "Marketplace",
                JOptionPane.QUESTION_MESSAGE);
        String password = JOptionPane.showInputDialog(null, "Enter your password", "Marketplace",
                JOptionPane.QUESTION_MESSAGE);
        input += username + " " + password;
        return input;
    }

    public String readCreate() {
        String input = "create ";
        Scanner s = new Scanner(System.in);
        boolean valid = false;
        boolean validTwo = false;
        boolean validThree = false;
        String email = "";
        String username = "";
        String password = "";
        String[] roleOption = {"Seller", "Customer"};
        do {
            email = JOptionPane.showInputDialog(null, "Enter your email", "Marketplace",
                    JOptionPane.QUESTION_MESSAGE);
            if (!email.contains("@") || !email.contains(".") || email.contains("|")) {
                JOptionPane.showMessageDialog(null, "Email not valid. Please try again or login"
                        , "Marketplace", JOptionPane.ERROR_MESSAGE);
            } else {
                valid = true;
            }
        } while (!valid);

        do {
            username = JOptionPane.showInputDialog(null, "Enter your username", "Marketplace",
                    JOptionPane.QUESTION_MESSAGE);
            if (username.contains("|")) {
                JOptionPane.showMessageDialog(null, "Username cannot contain a '|' symbol"
                        , "Marketplace", JOptionPane.ERROR_MESSAGE);
            } else {
                validTwo = true;
            }
        } while (!validTwo);
        do {
            password = JOptionPane.showInputDialog(null, "Enter your password", "Marketplace",
                    JOptionPane.QUESTION_MESSAGE);
            if (username.contains("|")) {
                JOptionPane.showMessageDialog(null, "Password cannot contain a '|' symbol"
                        , "Marketplace", JOptionPane.ERROR_MESSAGE);
            } else {
                validThree = true;
            }
        } while (!validThree);

        String role = (String) JOptionPane.showInputDialog(null, "Select role", "Marketplace",
                JOptionPane.PLAIN_MESSAGE, null, roleOption, null);

        input += email + " " + username + " " + password + " " + role;
        return input;


    }

    public String readMainInput() {
        String input = "";
        String[] introOptions = {"Login", "Create Account", "Exit"};
        String choice = (String) JOptionPane.showInputDialog(null, "Welcome to Marketplace:Select Option", "Marketplace",
                JOptionPane.PLAIN_MESSAGE, null, introOptions, null);
        switch (choice) {
            case "Login" -> input = "login";
            case "Create Account" -> input = "create";
            case "Exit" -> input = "exit";

            default -> System.out.println("Invalid choice. Please try again.");
        }

        return input;
    }

}

public class MarketplaceClient {

    public static void main(String[] args) {
        try {
            Helper help = new Helper();
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                System.out.print("Enter a command (type 'exit' to quit): ");
                String command = help.readMainInput();
                if (command.equals("login"))
                    command = help.readLogin();
                else if (command.equals("create"))
                    command = help.readCreate();
                else if (command.equals("exit"))
                    break;

                writer.println(command);
                String response = serverReader.readLine();
                if (response.equals("seller")) {
                    command = help.readSeller();
                    while (!command.equals("exit")) {
                        writer.println(command);
                        response = serverReader.readLine();
                        System.out.println("Hello seller:  \n" + response.replace("$", "\n"));
                        command = help.readSeller();
                    }
                } else if (response.equals("customer")) {
                    command = help.readCustomer();
                    while (!command.equals("exit")) {
                        writer.println(command);
                        response = serverReader.readLine();
                        String[] allResponse = response.split("\\$");
                        JOptionPane.showInputDialog(null, "Seller Menu", "Marketplace",
                                JOptionPane.PLAIN_MESSAGE, null, allResponse, null);
                        System.out.println("Hello customer: \n" + response.replace("$", "\n"));
                        command = help.readCustomer();
                    }
                } else if (response.equals("failure")) {
                    System.out.println(response);
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

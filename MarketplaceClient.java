import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

class Helper
{
    public String readCustomer()
    {
        Scanner s= new Scanner(System.in);
        System.out.println("Customer Menu:");
        System.out.println("1. View Marketplace");
        System.out.println("2. Select Product");
        System.out.println("3. Sort Marketplace");
        System.out.println("4. Search Marketplace");
        System.out.println("5. View Shopping Cart");
        System.out.println("6. purchases");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();
        String input="";
        s.nextLine();
        switch (choice) {
            case 1:
                input="view";
                break;
            case 2:
                System.out.println("Enter desired Product name");
                input="product ";
                String desiredProductName = s.nextLine();
                input+=desiredProductName+" ";
                System.out.println("1: Buy Product");
                System.out.println("2. Add Product to Cart");
                int productChoice = s.nextInt();
                s.nextLine();
                switch (productChoice) {
                    case 1:
                        System.out.println("Enter desired purchase Quantity");
                        int quantity = s.nextInt();
                        s.nextLine();
                        input+="buy "+quantity;
                        break;
                    case 2:
                        input+="add ";
                        break;
                    default:
                        System.out.println("Please enter 1 or 2");
                        break;
                }
                break;

            case 3:
                System.out.println("Sort by quantity or price?");
                System.out.println("1. Quantity");
                System.out.println("2. Price");
                int sortChoice = s.nextInt();
                s.nextLine();
                //not working cant figure out why
                switch (sortChoice) {
                    case 1:
                        input="sort quantity";

                        break;
                    case 2:
                        input="sort price";
                        break;
                    default:
                        System.out.println("Please enter 1 or 2");
                        break;
                }
                break;
            case 4:
                System.out.println("Enter search term");
                String searchTerm = s.nextLine();
                input="search "+searchTerm;
                break;
            case 5:
                input="cart";
                break;
            case 6:
                input="purchases";
                break;
            case 7:
                input="exit";
                break;
            default:
                System.out.println("Please enter number 1 through 7");
                break;
        }
        return input;
    }
    public String readSeller()
    {
        String input="";
        Scanner s= new Scanner(System.in);
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
                input="createstore ";
                System.out.println("enter desired store name");
                storeName = s.nextLine();
                input+=storeName;
                break;
            case 2:
                input="addproduct ";
                System.out.println("Enter the store name");
                storeName = s.nextLine();
                System.out.println("Please enter the name of product for store");
                productName = s.nextLine();
                System.out.println("Please enter the description of product for store");
                productDescription = s.nextLine().replace(" ","-");
                System.out.println("Please enter the quantity of product for store");
                productquantity = s.nextInt();
                s.nextLine();
                System.out.println("Please enter the price of product for store");
                productPrice = s.nextDouble();
                s.nextLine();
                input+=storeName+" "+productName+" "+productDescription+" "+productquantity+" "+productPrice;
                break;
            case 3:
                input="removeproduct ";
                System.out.println("Enter the store name");
                storeName = s.nextLine();
                System.out.println("Enter name of product to remove");
                productName = s.nextLine();
                input+=storeName+" "+productName;
                break;
            case 4:
                input="edit ";
                System.out.println("Enter the store name");
                storeName = s.nextLine();
                System.out.println("Enter name of product to Edit");
                productName = s.nextLine();



                System.out.println("Please enter the new name of product for store");
                String productnewName = s.nextLine();
                System.out.println("Please enter the new description of product for store");
                productDescription = s.nextLine().replace(" ","-");
                System.out.println("Please enter the new quantity of product for store");
                productquantity = s.nextInt();
                s.nextLine();
                System.out.println("Please enter the new price of product for store");
                productPrice = s.nextDouble();
                s.nextLine();
                input+=storeName+" "+productName+" "+productnewName+" "+productDescription+" "+productquantity+" "+productPrice;
                break;
            case 5:
                input = "viewsales";
                break;
            case 6:
                System.out.println("1. Customer Statistics");
                System.out.println("2. Product Statistics");
                int statisticChoice = s.nextInt();
                s.nextLine();
                input = switch (statisticChoice) {
                    case 1 -> "statistics customer";
                    case 2 -> "statistics product";
                    default -> input;
                };
                break;

            case 7:
                input = "exit";
            default:
                System.out.println("Enter a number 1 through 8");

        }
        return input;
    }
    public String readLogin()
    {
        String input="login ";

        Scanner s= new Scanner(System.in);
        System.out.println("Enter username or email");
        String username = s.nextLine();
        System.out.println("Enter password");
        String password = s.nextLine();
        input+=username+" "+password;
        return input;
    }
    public String readCreate()
    {
        String input="create ";
        Scanner s = new Scanner(System.in);
        System.out.println("Enter email");
        String email = s.nextLine();
        System.out.println("Please enter  username");
        String username = s.nextLine();
        System.out.println("Please enter desired password");
        String password = s.nextLine();
        System.out.println("Please enter desired role:Seller/Customer");
        String role = s.nextLine();

        input += email+" "+username+" "+password+" "+role;
        return input;


    }
    public  String readMainInput() {
        String input="";
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to the Marketplace!");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();
        s.nextLine();
        switch (choice) {
            case 1 ->
                    input="login";
            case 2 -> input="create";
            case 3 -> {
                input="exit";
            }
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
                if(command.equals("login"))
                    command = help.readLogin();
                else if(command.equals("create"))
                    command = help.readCreate();
                else if (command.equals("exit"))
                    break;

                writer.println(command);
                String response = serverReader.readLine();
                if(response.equals("seller"))
                {
                    command = help.readSeller();
                    while(!command.equals("exit"))
                    {
                        writer.println(command);
                        response = serverReader.readLine();
                        System.out.println("Hello seller:  \n"+response.replace("$","\n"));
                        command = help.readSeller();
                    }
                }
                else if(response.equals("customer")) {
                    command = help.readCustomer();
                    while(!command.equals("exit"))
                    {
                        writer.println(command);
                        response = serverReader.readLine();
                        System.out.println("Hello customer: \n"+response.replace("$","\n"));
                        command = help.readCustomer();
                    }
                }
                else if(response.equals("failure"))
                {
                    System.out.println(response);
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




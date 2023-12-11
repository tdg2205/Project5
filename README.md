# Project_5
Project 5 Option 3

Compilation and Running
----------------------------------------------------------------------------------------
Open a terminal or command prompt, navigate to the directory containing your Java files, and compile them using the javac command. For instance:- javac MarketplaceServer.java

To run the program write javac MarketplaceServer.java then run javac MarketplaceClient.java

The application will start. Kindly provide the necessary information as prompted during the execution.


Submitted
----------------------------------------------------------------------------------------
Lohit Jagarlamudi - Submitted Report on Brightspace 
Tyler Gentry - Submitted Vocareum Workspace


Classes and Methods
----------------------------------------------------------------------------------------

ClientHandler - Manages communication with a marketplace server

run() - Implements the Runnable interface, handles client requests in a loop.

processCommand - Processes client commands and returns corresponding results.

search - Searches for products in the marketplace.

addProductToCart - Adds a product to the shopping cart.

buyProduct - Attempts to buy a specified quantity of a product.

editProduct - Edits product details.

removeProduct - Removes a product from a store.

createStore - Creates a new store.

addProduct - Adds a product to a store.

productStatistics - Retrieves product-related statistics.

customerStatistics - Retrieves customer-related statistics.

viewSales - Displays sales information.

viewMarketplace - Displays information about all products in the marketplace.

selectProduct - Selects a product.

viewShoppingCart - Displays the contents of the shopping cart.

getUserByUsername - Retrieves a user by username.

getStoreByName - Retrieves a store by name.

getUserShoppingCart - Retrieves the shopping cart of the current user.

createUser - Creates a new user.

login - Performs user login.

Relationship- Managing communication between the marketplace server and individual clients.

--------------------------------------------

Customer - Customer class with shopping features for a marketplace

setPurchaseCount -  Sets the purchase count for the customer.

setPurchasedItems - Sets the list of purchased items for the customer.

setShoppingCart - Sets the shopping cart for the customer.

getPurchaseCount - Retrieves the purchase count for the customer.

getPurchasedItems - Retrieves the list of purchased items for the customer.

getShoppingCart - Retrieves the shopping cart for the customer.

addToShoppingCart - Adds a product to the customer's shopping cart.

purchaseProduct - Attempts to purchase a specified quantity of a product from the marketplace.

searchProducts - Searches the marketplace for products matching a given search term, returning a list of matching products.

Relationship - Interacts with Marketplace and Product classes for shopping

--------------------------------------------

Marketplace - Represents an online marketplace with various functionalities

readProduct - Reads product data from a file.

readStore - Reads store data from a file.

readUser -  Reads user data from a file.

create - Creates a new user (customer or seller) and handles input validation.

login - Authenticates users based on the provided credentials.

sortQuantity - Sorts products by quantity.

sortPrice - Sorts products by price.

createStore - Creates a new store for the current user.

editProduct - Edits product details.

removeProduct - Removes a product from a store.

addProduct - Adds a product to a store.

customerStatistics - Retrieves customer-related statistics for the current user's stores.

productStatistics - Retrieves product-related statistics for the current user's stores.

addProductToCart - Adds a product to the current customer's shopping cart.

buyProduct - Attempts to buy a specified quantity of a product for the current customer.

viewPurchases - Displays the purchased items of the current customer.

search - Searches for products in the marketplace.

view - Displays information about all products in the marketplace.

Relationship - The basis of other classes.

--------------------------------------------

MarketlpaceClient - Acts as the user interface to access other classes and input commands

main- Gives you the choice to either login, create an account, or exit before redirecting you to the method of your choice.

login - Allows you to login to your account by inputting both your email and password.

logOut - Logs out the current user of the interface.

createAccount - Allows you to create an account through inputting an email, password, your desired role, and name, checking if any users already contain the entered email so no duplicates are made.

sellerMenu - A menu for sellers that allow the user to add a product, remove a product, view sales, or logout.

customerMenu - A menu for customer that allow the user to view marketplace, purchase a product, contact seller by providing email, or logout.

Relationship - Implements other main classes.

--------------------------------------------

MarketplaceServer - A server for marketplace, handles multiple clients.

Relationship - To facilitate communication between the marketplace server and multiple clients.

--------------------------------------------

Product- A class that represents a product in the marketplace.

Relationship- Encapsulates the properties and behaviors of a product within the marketplace.

--------------------------------------------

Seller - A class that represents seller users in the marketplace

getYourStores - Returns the ArrayList of stores owned by the seller.

createYourStore - Adds a new store to the seller's list of owned stores.

addProduct - Allows a seller to add a product to an arraylist of products

removeProduct - Allows a seller to remove a product from an arraylist of products

editProduct - Allows a seller to edit a product by checking current products and setting wanted one to the new values. If no product was found tells user.

Relationship - Used in seller section of AppInterface.

--------------------------------------------

Store - A class that represents a virtual store in the marketplace.

Relationship - Store holds products, belongs to an owner, and records purchases.

--------------------------------------------

User - A class that represents a user with all neccesary attributes

Relationship - Base for creation of the customer and seller

--------------------------------------------

Tests.md- Creates test cases which work as instructions to do each function in the program.

# Project_5_Option_3
TESTS CASES
-----
Test 1. Seller Create Account
-
1. select create account
2. enter gentry@.mail.com for email
3. enter teegee for username
4. enter tyler for password
5. select seller as role
6. expected result : creates account, saves account in user database, and directs to seller options
7. given result: created and saved accounted and sent to seller options

Test 2. Seller Logout
-
continued from previous test
1. access seller menu and select logout
2. expected: loggedout and returned to starting menu
3. given: same as expected

Test 3. Seller Login 
-
1. select login
2. enter teegee
3. enter tyer
4. expected: logged in successfully
5. given: same as expected

Test 4. Seller Create Store
-
1.login
2.select create store
3.enter store name 'nike'
4.expected: created store name nike
5.given: same as expected

Test 5. Seller Add Product
-
1.select add product
2.enter store name nike
3.add product name shoes
4.add product description 'smooth nike kicks'
5.add quantity 2
6.add price 20
7.expected:item created in desired way
8.given:same as expected

Test 6. Seller Remove Product
-
1.select remove product
2.enter nike
3.enter shoes
4.expected:product removed
5.given:same as expected

Test 7. Seller Edit Product
-
1.select edit product
2.enter nike
3.enter shoes
4.enter new name,description,quantity, and price
5.jeans,blue jeans,3,15
6.expected:product updated with new values
7.given:same as expected

Test 7. Customer Create Account
-
1. select create account
2. enter krish@.mail.com for email
3. enter krish for username
4. enter sharma for password
5. select customer as role
6. expected result : creates account, saves account in user database, and directs to seller options
7. given result: created and saved accounted and sent to seller options

Test 8. Customer Logout
-
continued from previous test
1. access seller menu and select logout
2. expected: loggedout and returned to starting menu
3. given: same as expected

Test 9. Customer Login
-
1. select login
2. enter krish
3. enter sharma
4. expected: logged in successfully
5. given: same as expected

Test 10. Customer View Marketplace
-
1.select view marketplace
2.expected:see nike jeans for 15 dollars
3.given:same as expected

Test 11. Customer Buy Product
-
1.select select product
2.select buy
3.enter jeans
4.enter 1 to purchase 1
5.expected:purchase successful and jeans quantity changed from 2 to 3
6.given:same as expected

Test 12. Customer Add Product to Cart
-
1.select select product
2.select add to cart
3.enter jeans
4.expected:add to cart successful
5.given:same as expected

Test 13. Customer Sort Marketplace by Price
-
1. select sort marketplace then price
2. expected:marketplace sorted
3. given:same as expected

Test 14. Customer Sort Marketplace by Quantity
-
1. select sort marketplace then quantity
2. expected:marketplace sorted
3. given:same as expected

Test 15. Customer Search Marketplace
-
1. select search marketplace
2. enter search term blue
3. expected:nike jeans 15 dollars
4. given:same as expected

Test 16. Customer View Shopping Cart 
-
1. select view shopping cart.
2. expected:nike jeans 15 quantity available2
3. given:same as expected

Test 17. Customer View Purchases 
-
1. select view purchases
2. expected:nike jean 15
3. given:same as expected

Test 18. Seller View Sales
-
1. select view sales
2. expected:see all customer purchases tyler
3. given:same as expected

Test 19. Seller View Customer Statistics
-
1. select view statistics then customer
2. expected:see all customer statistics tyler 1 purchase
3. given:same as expected


Test 20. Seller View Product Statistics
-
1. select view statistics then product
2. expected:see all product statistics jeans 1 purchase
3. given:same as expected


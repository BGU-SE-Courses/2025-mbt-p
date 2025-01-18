# Software Quality Engineering - System Testing
This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [$$*TODO* software name$$](https://address-of-the-project.com).

In this assignment, we tested an open-source e-commerce software called OpenCart (https://github.com/opencart/opencart).
OpenCart is a popular PHP-based online store management system which supports multiple languages and currencies, and is freely available under the GNU General Public License.

## Installation
To set up the testing environment for this project, follow these steps:

1. Download the Repository:
   Clone or download the repository containing the OpenCart setup and testing scripts from the provided link (insert the GitHub repository link here).

2. Run Docker:
   a. Navigate to the root directory of the project where the docker-compose.yml file is located.
   b. Run the following command to start the necessary services (e.g., web server, database) using Docker: docker-compose up -d
   c. This command will set up an isolated local environment containing all the services required by OpenCart.

3. Run Selenium Server:
   a. Ensure you have Java installed on your machine to run the Selenium server, and make sure the chromedriver.exe's version matches your chrome version.
   b. Start the Selenium server, which is necessary for running browser-based tests.
   c. Run the Selenium server using the following command: ./selenium.bat

4. Testing with Cucumber and Provengo:
   a. Ensure that your testing frameworks (Cucumber and Provengo) are set up and configured to connect to the Selenium server.
   b. Use the feature files located in the respective directories for each testing method to execute your tests.

## What we tested
1. On the Cucumber part, we tested:
   We tested the inventory management and purchasing modules of the OpenCart system, focusing on administrative and user functionalities. 

   Admin Inventory Management
      Module: Inventory Management
      
      User story: An admin deletes an existing item from the inventory.
      Preconditions: The admin is logged into the OpenCart dashboard and has navigational access to the Product Page.
      Expected outcome: After deletion, the specified item (e.g., "iPhone") should no longer appear in the product list, ensuring the inventory reflects the updated status.
   
   User Purchase Process
      Module: Purchase Functionality

      User story 1: A user successfully purchases a product from the store.
      Preconditions: A user is registered and logged into the OpenCart system. The desired product (e.g., "iMac") is in stock, and the user has valid payment and shipping information.
      Expected outcome: The user can add the product to their cart, complete the checkout process, and receive confirmation that the cart is empty post-purchase, indicating a successful transaction.
      
      User story 2: A user attempts to purchase a product with insufficient stock.
      Preconditions: A user is logged into the OpenCart system, attempts to purchase a quantity of a product (e.g., "iMac") that exceeds the available stock.
      Expected outcome: The system should display an error message indicating that the desired quantity of the product is not available or the product is out of stock, preventing the transaction from completing.

## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results
Update all README.md files (except for d-e, see Section 1). Specifically, replace all $$*TODO*…$$ according to the instructions inside the $$.

## Detected Bugs
We detected the following bugs:

1. Bug 1: 
   1. General description: ...
   2. Steps to reproduce: ...
   3. Expected result: ...
   4. Actual result: ...
   5. Link to the bug report: (you are encouraged to report the bug to the developers of the software)
2. Bug 2: ...

$$*TODO* if you did not detect the bug, you should delete this section$$  

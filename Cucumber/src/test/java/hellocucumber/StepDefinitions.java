package hellocucumber;

import io.cucumber.java.en.*;
import io.cucumber.java.After;

public class StepDefinitions {
    private static final String CHROME_DRIVER_PATH = "C:\\Users\\tnayd\\OneDrive\\Documents\\2025-mbt-p-main\\Selenium\\chromedriver.exe";
    private AdminSteps adminSteps;
    private UsersSteps usersSteps;

    // Logs in as an admin with username: admin and password: admin
    @Given("Admin is logged in")
    public void admin_is_logged_in() throws InterruptedException {
        if (adminSteps == null) {
            adminSteps = new AdminSteps(CHROME_DRIVER_PATH);
        }
        adminSteps.admin_is_logged_in();
    }

    // Navigates to the Products page - choosing Catalog -> Products
    @When("Admin navigates to the Product Page")
    public void admin_navigates_to_product_page() throws InterruptedException {
        adminSteps.admin_navigate_to_product_page();
    }

    // Deletes the item with the given name - choosing the item and clicking the delete button
    @When("Admin deletes the item {string}")
    public void admin_deletes_the_item(String string) throws InterruptedException {
        adminSteps.admin_delete(string);
    }

    // Makes sure the item with the given name is no longer in the product list
    @Then("{string} should no longer appear in the product list")
    public void product_should_no_longer_appear_in_product_list(String itemName) throws InterruptedException {
        adminSteps.not_in_products(itemName);
    }

    // Opens the home page
    @Given("User is on the Home Page")
    public void user_is_on_home_page() {
        usersSteps = new UsersSteps(CHROME_DRIVER_PATH);
        usersSteps.user_is_on_home_page();
    }

    // Logs in as a user with the given email and password
    @When("User logs in with {string} {string}")
    public void user_logs_in_with(String email, String password) {
        usersSteps.user_logs_in_with(email, password);
    }

    // Searches for the item with the given name, and adds it to the cart, then checks out
    @When("User adds {string} units of {string} to the cart and checks out")
    public void user_adds_item_to_cart_and_checkout(String quantity, String itemName) throws InterruptedException {
        usersSteps.user_adds_item_to_cart_and_checkout(itemName, quantity);
    }

    // Fill in the address form with the given information
    @When("User selects an address for delivery: {string} {string} {string} {string} {string} {string} {string}")
    public void user_selects_address(String firstName, String lastName, String address,
                                      String city, String postcode, String country, String state) throws InterruptedException {
        usersSteps.user_selects_address(firstName, lastName, address, city, postcode, country, state);
    }

    // Checks out the order
    @When("User confirms the order")
    public void user_confirms_order() throws InterruptedException {
        usersSteps.user_confirms_order();
    }

    // Makes sure the order is successful - checks if the cart is empty
    @Then("The cart should be empty")
    public void cart_is_empty() throws InterruptedException {
        usersSteps.cart_is_empty();
    }

    // Makes sure an error message is displayed
    @Then("an error message should be displayed saying {string}")
    public void validate_error_message(String expectedMessage) {
        usersSteps.validate_error_message(expectedMessage);
    }


    @After
    public void tearDown() {
        if (adminSteps != null && adminSteps.driver != null) {
            System.out.println("Closing AdminSteps driver...");
            adminSteps.driver.quit();
        }
        if (usersSteps != null && usersSteps.driver != null) {
            System.out.println("Closing UsersSteps driver...");
            usersSteps.driver.quit();
        }
    }
}

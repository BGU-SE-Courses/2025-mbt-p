package hellocucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;



import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersSteps {
    public WebDriver driver;
    private final WebDriverWait wait;
    private String CHROME_DRIVER_PATH;

    public UsersSteps(String chromeDriverPath) {
        this.CHROME_DRIVER_PATH = CHROME_DRIVER_PATH;
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Navigate to the home page
    public void user_is_on_home_page() {
        driver.get("http://localhost/");
    }

    // Log in as a user with the given email and password
    public void user_logs_in_with(String email, String password) {
        try {
            // locate the "My Account" dropdown button
            WebElement myAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(@class, 'dropdown-toggle') and contains(., 'My Account')]")
            ));

            // Scroll to the "My Account" button and click it
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", myAccountButton);
            Thread.sleep(1000);
            myAccountButton.click();

            // Wait for the dropdown menu and click the "Login" option
            WebElement loginOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[contains(text(), 'Login')]")
            ));
            Thread.sleep(1000);
            loginOption.click();

            // fill in the login form
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@name='email']")
            ));
            emailInput.sendKeys(email);
            Thread.sleep(1000);

            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@name='password']")
            ));
            passwordInput.sendKeys(password);
            Thread.sleep(1000);

            // locate and click the Login button
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit' and contains(@class, 'btn-primary')]")
            ));
            Thread.sleep(1000);
            loginButton.click();

            // validate successful login (wait until the "Logout" option is visible)
            WebElement logoutOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(@class, 'list-group-item') and contains(text(), 'Logout')]")
            ));
            assertTrue(logoutOption.isDisplayed(), "Login was unsuccessful."); // if the logout option is visible, then login was successful. otherwise it failed

        } catch (Exception e) {
            System.err.println("Error during login process: " + e.getMessage());
        }
    }

    // Search for the item with the given name, add it to the cart, and proceed to checkout
    public void user_adds_item_to_cart_and_checkout(String itemName, String quantity) {
        try {
            search_an_item(itemName);
            Thread.sleep(2000);

            add_quantity_to_cart(quantity);
            Thread.sleep(2000);

            navigate_to_checkout();
            Thread.sleep(2000);

            // Check for insufficient stock error message on the checkout page
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'alert-danger') and contains(text(), 'not available')]")
                ));
                if (errorMessage.isDisplayed()) {
                    System.err.println("Error: Insufficient stock for item '" + itemName + "'.");
                    System.out.println("Error Message: " + errorMessage.getText());
                    return; // Exit the method since the checkout cannot proceed
                }
            } catch (Exception e) {
                System.out.println("No insufficient stock error on the checkout page. Proceeding...");
            }

        } catch (Exception e) {
            System.err.println("Error in adding item to cart and checking out: " + e.getMessage());
        }
    }

    // Navigate to the checkout page
    public void navigate_to_checkout() throws InterruptedException {
        // Re-locate the cart dropdown element after DOM updates
        WebElement cartDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'btn-dark') and contains(@class, 'dropdown-toggle')]")
        ));
        Thread.sleep(2000);
        cartDropdown.click();

        // Click the "Checkout" button
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'route=checkout/checkout') and contains(., 'Checkout')]")
        ));
        Thread.sleep(1000);
        checkoutButton.click();
    }

    // Add the desired quantity of the item to the cart
    public void add_quantity_to_cart(String quantity) throws InterruptedException {
        // Set the desired quantity
        WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='quantity']")
        ));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        Thread.sleep(1000);

        // Click "Add to Cart" button
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='button-cart']")
        ));
        Thread.sleep(1000);
        addToCartButton.click();
        // Wait for any pop-up messages to clear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".alert-success") // Adjust this locator if needed for success messages
        ));
        Thread.sleep(4000);
    }

    // Search for the item with the given name
    public void search_an_item(String itemName) throws InterruptedException {
        // Search for the item
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='search']")
        ));
        searchInput.clear();
        searchInput.sendKeys(itemName);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and contains(@class, 'btn-light btn-lg')]")
        ));
        Thread.sleep(1000);
        searchButton.click();

        // Wait for the product list to appear
        WebElement productList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("product-list")
        ));

        // Locate the product link within the product list
        WebElement itemLink = productList.findElement(By.xpath(".//a[contains(@href, 'product_id') and text()='" + itemName + "']"));
        // Scroll to the item's link to ensure visibility
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", itemLink);
        Thread.sleep(1000);
        itemLink.click();
    }


    // Validate that an error message is displayed
    public void validate_error_message(String expectedMessage) {
        try {
            Thread.sleep(2000);

            // Check if the expected message is anywhere on the page
            boolean isMessageDisplayed = driver.getPageSource().contains(expectedMessage);

            // Assert that the message is found
            assertTrue(isMessageDisplayed, 
                    "Expected error message: '" + expectedMessage + "' was not found on the page.");
            
        } catch (Exception e) {
            System.err.println("Error during error message validation: " + e.getMessage());
        }
    }

    // Fill in the address form with the given information
    public void user_selects_address(String firstName, String lastName, String address,
                                 String city, String postcode, String country, String state) {
        try {
            // Select "I want to use a new address" button
            WebElement newAddressRadioButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("input-shipping-new")
            ));
            if (!newAddressRadioButton.isSelected()) {
                newAddressRadioButton.click();
            }

            // Wait till the form is fully loaded
            WebElement formVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id='form-shipping-address']")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", formVisible);

            // Enter first name
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-shipping-firstname")))
                .sendKeys(firstName);

            Thread.sleep(1000);

            // Enter last name
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-shipping-lastname")))
                .sendKeys(lastName);
            
            Thread.sleep(1000);

            // Enter address
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-shipping-address-1")))
                .sendKeys(address);
            
            Thread.sleep(1000);

            // Enter city
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-shipping-city")))
                .sendKeys(city);
            
            Thread.sleep(1000);

            // Enter postcode
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-shipping-postcode")))
                .sendKeys(postcode);
            
            Thread.sleep(1000);

            // Open the country dropdown
            WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("input-shipping-country")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countryDropdown);
            countryDropdown.click();
            Thread.sleep(1000); 

            // Type the full country name and press Enter
            countryDropdown.sendKeys(country);
            Thread.sleep(500);
            countryDropdown.sendKeys(Keys.ENTER);
            Thread.sleep(3000);

            // Open the state dropdown
            WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("input-shipping-zone")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateDropdown);
            stateDropdown.click(); 
            Thread.sleep(1000);

            // Type the full state name and press Enter
            stateDropdown.sendKeys(state);
            Thread.sleep(500);
            stateDropdown.sendKeys(Keys.ENTER);
            Thread.sleep(2000);

            // Click the "Continue" button
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='form-shipping-address']/div/button[1]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
            continueButton.click();

            Thread.sleep(2000);
            
            // Choose shipping method
            WebElement shippingChooseButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("button-shipping-methods")
            ));
            Thread.sleep(2000);
            shippingChooseButton.click();
            Thread.sleep(2000);
            WebElement shippingContinueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("button-shipping-method")
            ));
            Thread.sleep(2000);
            shippingContinueButton.click();
            Thread.sleep(2000);

            // Choose payment method
            WebElement paymentChooseButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("button-payment-methods")
            ));
            Thread.sleep(2000);
            paymentChooseButton.click();
            Thread.sleep(2000);
            WebElement paymentContinueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("button-payment-method")
            ));
            Thread.sleep(2000);
            paymentContinueButton.click();
            Thread.sleep(2000);

        } catch (Exception e) {
            System.err.println("Error in selecting address: " + e.getMessage());
        }
    }

    // Confirm the order
    public void user_confirms_order() throws InterruptedException {
        // Scroll to the confirm button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);

        // Click the "Confirm Order" button
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='btn btn-primary' and text()='Confirm Order']")
        ));
        confirmButton.click();
        Thread.sleep(3000);
    }

    // Check if the cart is empty
    public void cart_is_empty() throws InterruptedException {
        try {
            Thread.sleep(1000);
            // Wait for the cart button to be visible
            WebElement cartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(@class, 'btn-dark') and contains(@class, 'dropdown-toggle')]")
            ));
            Thread.sleep(1000);
            // Get the text from the cart button
            String cartText = cartButton.getText();

            // Extract the number of items from the text
            Pattern pattern = Pattern.compile("(\\d+) item\\(s\\)");
            Matcher matcher = pattern.matcher(cartText);
            if (matcher.find()) {
                String numberString = matcher.group(1);
                int number = Integer.parseInt(numberString);

                // Assert that the cart is empty (number of items is 0)
                assertEquals(0, number, "The cart is not empty.");
            } else {
                throw new AssertionError("Unable to find item count in cart text.");
            }
        } catch (Exception e) {
            System.err.println("Error while checking if the cart is empty: " + e.getMessage());
            throw e; // Re-throw the exception to fail the test
        }
    }


    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}

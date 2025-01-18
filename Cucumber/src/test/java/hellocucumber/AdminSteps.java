package hellocucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminSteps {
    public WebDriver driver;
    private WebDriverWait wait;
    private final String CHROME_DRIVER_PATH;

    public AdminSteps(String CHROME_DRIVER_PATH) {
        this.CHROME_DRIVER_PATH = CHROME_DRIVER_PATH;
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // Logs in as an admin with username: admin and password: admin
    public void admin_is_logged_in() throws InterruptedException {
        driver.get("http://localhost/admin");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-username"))).sendKeys("admin");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password"))).sendKeys("admin");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(2000);

        // Handle the security notification modal
        try {
            WebElement securityNotification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'modal-dialog')]")));
            WebElement closeButton = securityNotification.findElement(By.xpath("//button[contains(@class, 'close')]"));
            Thread.sleep(1000);
            closeButton.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("No security notification modal found.");
        }

        // Validate successful login by ensuring the dashboard is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
        Thread.sleep(2000);
    }

    // Navigates to the Products page - choosing Catalog -> Products
    public void admin_navigate_to_product_page() throws InterruptedException {
        try {
            // Click on "Catalog" on the left menu
            WebElement catalogMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("menu-catalog")));
            Thread.sleep(1000);
            catalogMenu.click();
            Thread.sleep(2000);

            // Click on "Products" on the catalog submenu
            WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products")));
            Thread.sleep(1000);
            productsLink.click();
            Thread.sleep(2000);

            // Validate that the products page loaded
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Products')]")));
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Failed to navigate to the product page: " + e.getMessage());
        }
    }

    // Deletes the item with the given name - choosing the item and clicking the delete button
    public void admin_delete(String itemName) throws InterruptedException {
        try {
            search_product(itemName);
            Thread.sleep(2000);
            By checkboxLocator = By.cssSelector("table tbody tr td input[type='checkbox']");
            Thread.sleep(2000);
            retryClick(checkboxLocator);
            Thread.sleep(2000);
            By deleteButtonLocator = By.xpath("//*[@id='content']/div[1]/div[1]/div[1]/button[3]");
            Thread.sleep(2000);
            retryClick(deleteButtonLocator);
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
            Thread.sleep(3000);
            
        } catch (Exception e) {
            System.err.println("Error during product deletion: " + e.getMessage());
        }
    }

    // Retries clicking an element in case of a StaleElementReferenceException
    public void retryClick(By by) {
        final int maxAttempts = 5;
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                driver.findElement(by).click();
                return; // Success
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == maxAttempts) throw e;
            }
        }
    }

    // Makes sure the item with the given name is no longer in the product list
    public void not_in_products(String itemName) throws InterruptedException {
        Thread.sleep(3000);
        // Filter by product name
        search_product(itemName);

        // Ensure product is no longer in the list
        WebElement noResultsMessage = driver.findElement(By.xpath("//td[contains(text(),'No results!')]"));
        Thread.sleep(1000);
        assertTrue(noResultsMessage.isDisplayed(), "Product '" + itemName + "' should no longer appear in the product list.");
    }


    // Filter by product name
    public void search_product(String itemName) throws InterruptedException {
        // Filter by product name
        WebElement productNameFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='filter_name']")));
        Thread.sleep(1000);
        productNameFilter.clear();
        Thread.sleep(1000);
        productNameFilter.sendKeys(itemName);
        Thread.sleep(2000);
        driver.findElement(By.id("button-filter")).click();
        Thread.sleep(2000);
    }
}




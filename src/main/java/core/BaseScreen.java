package core;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import utils.Helper;

public class BaseScreen extends Helper {
    AppiumDriver appiumDriver;


    /**
     * Constructs a new BasePage and initializes the WebDriver instance for the current thread.
     */
    public BaseScreen() {
        super();
        this.appiumDriver = AppiumDriverManager.getDriver();
    }

    /**
     * Returns a WebDriverWait instance for the given wait time (in seconds).
     * @param waitTime The wait time in seconds.
     * @return WebDriverWait instance.
     */
    public WebDriverWait getWait(long waitTime) {
        return new WebDriverWait(this.appiumDriver, Duration.ofSeconds(waitTime));
    }

    private WebElement findElement(By selector) {
        if (selector == null) {
            throw new IllegalArgumentException("Selector cannot be null");
        }
        return getWait(TestSettings.WAIT_ELEMENT).until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    protected boolean isElementDisplayed(By selector) {
        try {
            return findElement(selector).isDisplayed();
        } catch (Exception e) {
            logger.warn("Element {} is not displayed: {}", selector, e.getMessage());
            return false;
        }
    }

    protected void tapElement(By selector) {
        WebElement element = findElement(selector);
        element.click();
        logger.info("Tapped on element: {}", selector);
    }

    protected void enterText(By selector, String text) {
        WebElement element = findElement(selector);
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text '{}' into element: {}", text, selector);
    }
}

package pages.wallethub;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseCase {

    protected static final Logger LOG = Logger.getLogger(LoginPage.class.getName());

    private WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;




    /* Method to instantiate the webdriver */
    public BaseCase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        this.actions = new Actions(driver);
    }

    protected void waitUntil(final Function<? super WebDriver, ?> condition) {
        wait.until(condition);
    }


    /* Method to Find Element */
    protected WebElement getWebElement(By selector) {
        waitUntil(ExpectedConditions.presenceOfElementLocated(selector));
        waitUntil(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElement(selector);
    }

    /* Method to Find Elements */
    protected List<WebElement> getWebElements(By selector) {
        waitUntil(ExpectedConditions.presenceOfElementLocated(selector));
        return driver.findElements(selector);
    }

    public String getElementText(By selector){
       return getWebElement(selector).getText();
    }



    /*Method to check if element is Present*/
    protected boolean elementIsPresent(final By selector) {
        try {
            waitUntil(ExpectedConditions.presenceOfElementLocated(selector));
            return driver.findElements(selector).size() > 0;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return false;
        }
    }




    /*Method to scroll to a specific element*/
    public void scrollToElement(By selector){
        getWebElement(selector).sendKeys(Keys.DOWN);
    }
}

package pages.wallethub;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;
import java.util.logging.Logger;

public class RateInsurancePage extends LoginPage{

    protected static final Logger LOG = Logger.getLogger(RateInsurancePage.class.getName());

    private WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    // Login Selector
    protected final By starSelector = By.cssSelector("[aria-label='4 star rating'][width='38']");



    // TODO: Hover on the Stars and Click on th 4th Star to redirect to the Review items Page
    // TODO: Fetch and assert the most recent review

    public RateInsurancePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        this.actions = new Actions(driver);
    }

    public void scrollToElement(By selector){
        getWebElement(selector).sendKeys(Keys.DOWN);
    }


    // todo Hovering on the 4th Star

    public void hoverOnElement(By selector ){
        scrollToElement(selector);
        actions
                .moveToElement(getWebElement(selector))
                .click(getWebElement(selector))
                .build()
                .perform();
    }


    //todo take note of Nullpointer exception if so change return to RateInsurancePage
    public void clickStars(){
        hoverOnElement(starSelector);
    }

    protected void waitUntil(final Function<? super WebDriver, ?> condition) {
        wait.until(condition);
    }









}

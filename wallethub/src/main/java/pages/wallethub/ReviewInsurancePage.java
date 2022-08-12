package pages.wallethub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;
import java.util.logging.Logger;

public class ReviewInsurancePage extends RateInsurancePage {

    protected static final Logger LOG = Logger.getLogger(ReviewInsurancePage.class.getName());

    private WebDriver driver;
    protected WebDriverWait wait;

    // Login Selector
    protected final By emailTextboxSelector = By.id("email");
    protected final By passwordTextboxSelector =By.id("password");
    protected final By logInButtonSelector = By.cssSelector(".blue");



    public ReviewInsurancePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    protected void waitUntil(final Function<? super WebDriver, ?> condition) {
        wait.until(condition);
    }



    public ReviewInsurancePage enterPassword(String password){
        getWebElement(passwordTextboxSelector).clear();
        getWebElement(passwordTextboxSelector).sendKeys(password);
        return this;
    }


    public ReviewInsurancePage clickLogin(){
        getWebElement(logInButtonSelector).click();
        return this;
    }







}

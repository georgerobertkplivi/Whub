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

public class LoginPage {

    protected static final Logger LOG = Logger.getLogger(LoginPage.class.getName());

    private WebDriver driver;
    protected WebDriverWait wait;

    // email Selector
    protected final By emailTextboxSelector = By.id("em-ipt");

    // password Selector
    protected final By passwordTextboxSelector = By.cssSelector("[placeholder='Password']");

    // login button Selector
    protected final By logInButtonSelector = By.cssSelector(".btn");

    // login tab Selector
    protected final By logInTabSelector = By.xpath("//a[.='Login']");



    /* Method to instantiate the webdriver */
    public LoginPage(WebDriver driver) {
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

    /*Method to type email*/
    public LoginPage enterEmail(String email){
        getWebElement(emailTextboxSelector).clear();
        getWebElement(emailTextboxSelector).sendKeys(email);
        return this;
    }



    /*Method to type password*/
    public LoginPage enterPassword(String password){
        getWebElement(passwordTextboxSelector).clear();
        getWebElement(passwordTextboxSelector).sendKeys(password);
        return this;
    }


    /*Method to click login Button*/
    public LoginPage clickLogin(){
        getWebElement(logInButtonSelector).click();
        return this;
    }

    /*Method to click login Tab*/
    public LoginPage clickLoginTab(){
        getWebElement(logInTabSelector).click();
        return this;
    }


    /*Method to login into the system*/
    public LoginPage login(String email, String password){
        assertTrue(getWebElement(By.cssSelector(".w-icon-wallet")).isDisplayed());
        clickLoginTab();
        enterEmail(email);
        enterPassword(password);
        clickLogin();

        assertTrue(getWebElement(By.cssSelector(".brgm-user > .brgm-list-title")).isDisplayed());
        return this;
    }

    /*Method to scroll to a specific element*/
    public void scrollToElement(By selector){
        getWebElement(selector).sendKeys(Keys.DOWN);
    }




    /**
    *
    * RATE INSURANCE PAGE IMPLEMENTATION
    *
    **/

    protected Actions actions;

    // Login Selector
    protected final By starSelector = By.cssSelector("[aria-label='4 star rating'][width='38']");



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


    public static String generateRandomText(int len) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString().toLowerCase();
    }


    /**
     *
     * REVIEW INSURANCE PAGE
     *
     **/




    public void clickPolicyDropdown(){
        final By dropdownSelector = By.xpath("//span[.='Select...']");
        getWebElement(dropdownSelector).click();

    }

    public void clickHealthInsurance(){
        final By healthInsuranceSelector = By.xpath("//li[.='Health Insurance']");
        getWebElement(healthInsuranceSelector).click();

    }

    public void clickSubmitButton(){
        final By submitButtonSelector = By.cssSelector(".sbn-action");
        getWebElement(submitButtonSelector).click();
    }

    public LoginPage writeReview(String reviewText){
        final By countDisplaySelector = By.xpath("//span[@class='bold-font color-aqua']");
        clickPolicyDropdown();
        clickHealthInsurance();
        getWebElement(By.cssSelector(".wrev-user-input")).sendKeys(reviewText);
        assertTrue(elementIsPresent(countDisplaySelector));
        clickSubmitButton();
        return this;
    }

    /**
     *
     * CONFIRMATION PAGE
     *
     **/

    final String confirmationPageTitle = "WalletHub - Review Confirmation";

    public void clickContinueButton(){
        final By submitButtonSelector = By.cssSelector(".rvc-continue-btn");
        if(elementIsPresent(submitButtonSelector)) {
            getWebElement(submitButtonSelector).click();
        }
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public LoginPage submitReview() throws InterruptedException {
        Thread.sleep(5000);
        elementIsPresent(reviewerNameSelector);
        assertEquals(getPageTitle(),confirmationPageTitle);
        clickContinueButton();
        return this;
    }

    public LoginPage isReviewPresent(String part_email){
        final By submitButtonSelector = By.xpath("//span[.='@" + part_email + "']");
        if(elementIsPresent(submitButtonSelector)){
            getWebElement(submitButtonSelector).getText().contains(part_email);
        }

        return this;
    }

    /**
     *
     *
     * REVIEW RESULTS
     *
     * **/

    final By reviewerNameSelector = By.cssSelector("[data-pos='0']");

    public String reviewPost(){

        return getWebElement(reviewerNameSelector).getText();
    }




    /*Go Back to RateInsurance Page to Look for the Just Submitted Review and Assert its details*/
    



}

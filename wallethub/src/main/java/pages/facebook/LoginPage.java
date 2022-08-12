package pages.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class LoginPage {

    protected static final Logger LOG = Logger.getLogger(LoginPage.class.getName());

    private WebDriver driver;
    protected WebDriverWait wait;

    // Login Selector
    protected final By emailTextboxSelector = By.id("email");
    protected final By passwordTextboxSelector = By.id("pass");
    protected final By storyPostSelector = By.cssSelector(".i09qtzwb.fi2e5rcv > .mm8kr34x");
    protected final By createStorySelector =By.cssSelector(".ljqsnud1.a8c37x1j");
    protected final By createStoryModalCardSelector = By.cssSelector("[href='/stories/create/'] > .goun2846");
    protected final By logInButtonSelector = By.cssSelector("[name='login']");
    protected final By storyTextAreaSelector = By.cssSelector(".oud54xpy");
    protected final By addStoryButtonSelector = By.xpath("//a[@href='https://web.facebook.com/stories/create/']//span[@class='a8c37x1j ni8dbmo4 stjgntxs l9j0dhe7 ltmttdrg g0qnabr5']");
    protected final By userProfileNameSelector = By.xpath("//div[@class='buofh1pr']/ul[1]//span[@class='a8c37x1j ni8dbmo4 stjgntxs l9j0dhe7']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    protected void waitUntil(final Function<? super WebDriver, ?> condition) {
        wait.until(condition);
    }

    // Find Element
    protected WebElement getWebElement(By selector) {
        waitUntil(ExpectedConditions.presenceOfElementLocated(selector));
        waitUntil(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElement(selector);
    }

    // Find Elements
    protected List<WebElement> getWebElements(By selector) {
        waitUntil(ExpectedConditions.presenceOfElementLocated(selector));
        return driver.findElements(selector);
    }

    protected boolean elementIsPresent(final By selector) {
        try {
            waitUntil(ExpectedConditions.presenceOfElementLocated(selector));
            return driver.findElements(selector).size() > 0;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return false;
        }
    }

    public LoginPage enterEmail(String email){
        getWebElement(emailTextboxSelector).clear();
        getWebElement(emailTextboxSelector).sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password){
        getWebElement(passwordTextboxSelector).clear();
        getWebElement(passwordTextboxSelector).sendKeys(password);
        return this;
    }


    public LoginPage enterStoryTest(String story){
        getWebElement(storyTextAreaSelector).clear();
        getWebElement(storyTextAreaSelector).sendKeys(story);
        return this;
    }

    public LoginPage clickLogin(){
        getWebElement(logInButtonSelector).click();
        return this;
    }
    public LoginPage clickCreateStoryCard(){
        getWebElement(createStorySelector).click();
        return this;
    }


    public LoginPage clickStoryModalCard(){
        getWebElement(createStoryModalCardSelector).click();
        return this;
    }




    public LoginPage clickUserProfileName(){
        getWebElement(userProfileNameSelector).click();
        return this;
    }


    public LoginPage clickStoryButton(){
        getWebElement(addStoryButtonSelector).click();
        return this;
    }

    public LoginPage clickLogoButton(){
        getWebElement(By.cssSelector(".p361ku9c")).click();
        return this;
    }

    public LoginPage clickShareStoryButton(){
        getWebElement(By.xpath("//*[contains(text(),'Share to Story')]")).click();
        return this;
    }


    public LoginPage clickCreateATextStoryCard(){
        getWebElement(By.xpath("//*[contains(text(),'Create a Text Story')]")).click();
        return this;
    }


    public LoginPage login(String email, String password){
        assertTrue(getWebElement(By.cssSelector("[alt='Facebook']")).isDisplayed());
        enterEmail(email);
        enterPassword(password);
        clickLogin();

        assertTrue(getWebElement(By.cssSelector(".p361ku9c")).isDisplayed());
        return this;
    }

    public LoginPage createStory( String story){
        enterStoryTest(story);
        clickShareStoryButton();
        clickLogoButton();
        assertTrue(elementIsPresent(storyPostSelector));
        return this;
    }

    public LoginPage addStoryPost( String story){
        clickLogoButton();
        if (elementIsPresent(createStoryModalCardSelector)){
            clickStoryModalCard();
        }else {
            clickCreateStoryCard();
        }
        clickCreateATextStoryCard();
        createStory(story);


        return  this;
    }


}

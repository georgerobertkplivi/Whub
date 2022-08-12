package pages.wallethub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertTrue;

public class LoginPageObject extends BaseCase{
    public LoginPageObject(WebDriver driver) {
        super(driver);
    }


    // email Selector
    protected final By emailTextboxSelector = By.id("em-ipt");

    // password Selector
    protected final By passwordTextboxSelector = By.cssSelector("[placeholder='Password']");

    // login button Selector
    protected final By logInButtonSelector = By.cssSelector(".btn");

    // login tab Selector
    protected final By logInTabSelector = By.xpath("//a[.='Login']");



    /*Method to type email*/
    public LoginPageObject enterEmail(String email){
        getWebElement(emailTextboxSelector).clear();
        getWebElement(emailTextboxSelector).sendKeys(email);
        return this;
    }



    /*Method to type password*/
    public LoginPageObject enterPassword(String password){
        getWebElement(passwordTextboxSelector).clear();
        getWebElement(passwordTextboxSelector).sendKeys(password);
        return this;
    }


    /*Method to click login Button*/
    public LoginPageObject clickLogin(){
        getWebElement(logInButtonSelector).click();
        return this;
    }

    /*Method to click login Tab*/
    public LoginPageObject clickLoginTab(){
        getWebElement(logInTabSelector).click();
        return this;
    }


    /*Method to login into the system*/
    public LoginPageObject login(String email, String password){
        assertTrue(getWebElement(By.cssSelector(".w-icon-wallet")).isDisplayed());
        clickLoginTab();
        enterEmail(email);
        enterPassword(password);
        clickLogin();

        assertTrue(getWebElement(By.cssSelector(".brgm-user > .brgm-list-title")).isDisplayed());
        return this;
    }



}

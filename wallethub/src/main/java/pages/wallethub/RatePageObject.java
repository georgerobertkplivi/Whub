package pages.wallethub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RatePageObject extends LoginPageObject{
    public RatePageObject(WebDriver driver) {
        super(driver);
    }

    // Login Selector
    protected final By starSelector = By.cssSelector("[aria-label='4 star rating'][width='38']");


/** Hover on a clickable element and click **/
    public void hoverOnElement(By selector ){
        scrollToElement(selector);
        actions
                .moveToElement(getWebElement(selector))
                .click(getWebElement(selector))
                .build()
                .perform();
    }

    public ReviewPageObject clickStars(){
        hoverOnElement(starSelector);
        return null;
    }


}

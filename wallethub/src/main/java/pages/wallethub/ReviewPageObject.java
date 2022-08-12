package pages.wallethub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class ReviewPageObject extends RatePageObject{
    public ReviewPageObject(WebDriver driver) {
        super(driver);
    }



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

    public ReviewPageObject writeReview(String reviewText){
        final By countDisplaySelector = By.xpath("//span[@class='bold-font color-aqua']");
        clickPolicyDropdown();
        clickHealthInsurance();
        getWebElement(By.cssSelector(".wrev-user-input")).sendKeys(reviewText);
        assertTrue(elementIsPresent(countDisplaySelector));
        clickSubmitButton();
        return this;
    }


}

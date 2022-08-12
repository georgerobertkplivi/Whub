package pages.wallethub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class ConfirmationPageObject extends ReviewPageObject{
    final By reviewerNameSelector = By.cssSelector("[data-rvid='2141087306'] .rvtab-ci-name");

    WebDriver driver;
    public ConfirmationPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


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

    public ConfirmationPageObject submitReview() throws InterruptedException {
        Thread.sleep(5000);
        elementIsPresent(reviewerNameSelector);
        assertEquals(getPageTitle(),confirmationPageTitle);
        clickContinueButton();
        return this;
    }

    public ConfirmationPageObject isReviewPresent(String reviewText){
        final By usernameSelector = By.cssSelector("[data-pos='0']");
        if(elementIsPresent(usernameSelector)){
            getWebElement(usernameSelector).getText().contains(reviewText);
        }

        return this;
    }


}

package test.wallethub;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.wallethub.ConfirmationPageObject;
import pages.wallethub.LoginDTO;
import pages.wallethub.RatePageObject;
import pages.wallethub.ReviewPageObject;

public class InsuranceTest extends BaseTest{
    public WebDriver driver;

    @Test
    public void createReview() throws InterruptedException {
        RatePageObject ratePage = new RatePageObject(driver);
        ConfirmationPageObject confirmationPage = new ConfirmationPageObject(driver);


        LoginDTO expectedData = setLoginData();

        gotoInsuranceReviewPage();

        ReviewPageObject reviewPage = ratePage.clickStars();

        confirmationPage.submitReview().isReviewPresent(expectedData.getPartEmail());

        System.out.println("Review was successful");
    }

    private LoginDTO setLoginData() {

        LoginDTO expectedData = new LoginDTO();

        expectedData.setReviewText("As is evident from the use case, we will be using the login text fields and the login button multiple times. First, we will see how our code would look like when we are not using the page object model design technique.");
        expectedData.setExpectedStars(4);

        return expectedData;
    }


}

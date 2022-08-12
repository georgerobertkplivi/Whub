package test.wallethub;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.wallethub.LoginDTO;

import static org.junit.Assert.assertTrue;


public class ReviewInsuranceTest extends BaseTestCase {
    public WebDriver driver;

    @Test
    public void createReview() throws InterruptedException {


        LoginDTO expectedData = setLoginData();

        gotoInsuranceReviewPage();
        loginPage.clickStars();
        loginPage
                .writeReview(expectedData.getReviewText())
                .submitReview()
                .isReviewPresent(expectedData.getPartEmail());
        assertTrue(loginPage.reviewPost().contains(expectedData.getReviewText().trim()));
        System.out.println("Login was successful");
    }

    private LoginDTO setLoginData() {

        LoginDTO expectedData = new LoginDTO();


//        expectedData.setReviewText(generateRandomText(130));
        expectedData.setReviewText("As is evident from the use case, we will be using the login text fields and the login button multiple times. First, we will see how our code would look like when we are not using the page object model design technique.");
        expectedData.setExpectedStars(4);

        return expectedData;
    }


}

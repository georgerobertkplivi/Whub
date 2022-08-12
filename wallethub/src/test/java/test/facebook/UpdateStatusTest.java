package test.facebook;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.facebook.LoginDTO;



public class UpdateStatusTest extends BaseTestCase {
    private WebDriver driver;

    @Test
    public void updateStory(){
        LoginDTO expectedData = setLoginData();

        loginPage
//                .login(expectedData.getEmail(), expectedData.getPassword())
                .addStoryPost(expectedData.getStoryText());
    }

    private LoginDTO setLoginData(){
        LoginDTO expectedData = new LoginDTO();

        expectedData.setStoryText("Hello World");

        return expectedData;
    }


}

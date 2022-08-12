package test.wallethub;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.wallethub.LoginDTO;
import pages.wallethub.LoginPage;
import pages.wallethub.LoginPageObject;
import pages.wallethub.ReviewPageObject;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {
    protected static final Logger LOG = Logger.getLogger(BaseTest.class.getName());


    private static WebDriver driver;
    protected LoginPageObject loginPage;

    public String email = "hacosi7018@ukgent.com";
    public String password = "Ymlilgee1991@";


    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        final String baseURL = "https://wallethub.com/join/light";

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);

        // Delete all cookies
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        LoginDTO expectedData = setLoginData();
        loginPage = new LoginPageObject(driver);
        loginPage.login(expectedData.getEmail(), expectedData.getPassword());


    }

    public ReviewPageObject gotoInsuranceReviewPage(){
        final String profileURL = "https://wallethub.com/profile/13732055i";
        driver.get(profileURL);
        return new ReviewPageObject(driver);
    }

    public String part_email(String email){

        return email.split("@")[0];
    }



    private LoginDTO setLoginData(){
        LoginDTO expectedData = new LoginDTO();

        expectedData.setEmail(email);
        expectedData.setPassword(password);
        expectedData.setPartEmail(part_email(email));

        return expectedData;
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

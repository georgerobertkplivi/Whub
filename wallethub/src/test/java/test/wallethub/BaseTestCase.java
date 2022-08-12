package test.wallethub;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.wallethub.LoginDTO;
import pages.wallethub.LoginPage;
import pages.wallethub.ReviewInsurancePage;

import java.util.concurrent.TimeUnit;

public class BaseTestCase {

    private static WebDriver driver;
    protected LoginPage loginPage;


    public String email = "tofan58557@logodez.com";
    public String password = "69q2@YHY/A.K-DV";



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
        loginPage = new LoginPage(driver);
        loginPage.login(expectedData.getEmail(), expectedData.getPassword());


    }

    public ReviewInsurancePage gotoInsuranceReviewPage(){
        final String profileURL = "https://wallethub.com/profile/13732055i";
        driver.get(profileURL);
        return new ReviewInsurancePage(driver);
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

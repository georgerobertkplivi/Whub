package test.facebook;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.facebook.LoginDTO;
import pages.facebook.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTestCase {

    private static WebDriver driver;
    protected LoginPage loginPage;

    @BeforeEach
    public void setUp() {
//        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        final String baseURL = "https://web.facebook.com/";
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);

        // Delete all cookies
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        LoginDTO expectedData = setLoginData();
        loginPage = new LoginPage(driver);
        loginPage.login(expectedData.getEmail(), expectedData.getPassword());


    }

    private LoginDTO setLoginData(){
        LoginDTO expectedData = new LoginDTO();

        expectedData.setEmail("george.kplivi@gmail.com");
        expectedData.setPassword("test1@123");

        return expectedData;
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

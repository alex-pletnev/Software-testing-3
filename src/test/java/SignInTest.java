import org.example.ConfProperties;
import org.example.pages.MainPage;
import org.example.pages.SignInPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class SignInTest {

    public static SignInPage signInPage;
    public static WebDriver chromeDriver;
    public static MainPage mainPage;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.get(ConfProperties.getProperty("mainpage"));
        mainPage = new MainPage(chromeDriver);
    }

    @AfterAll
    public static void close() throws InterruptedException {
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.close();
    }

    @BeforeEach
    public void setAuthPage() {
        chromeDriver.get(ConfProperties.getProperty("mainpage"));
        mainPage.getSingInButton().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        signInPage = new SignInPage(chromeDriver);
    }


    @DisplayName("Test login with password")
    @Test
    public void testAuthForm() throws InterruptedException {
        signInPage.getEmailInputField().click();
        signInPage.getEmailInputField().sendKeys(ConfProperties.getProperty("auth_email"));
        signInPage.getPasswordInputField().sendKeys(ConfProperties.getProperty("auth_password"));
        signInPage.getFinishSingInButton().click();
        MainPage mainPage = new MainPage(chromeDriver);
        String expected = "Home";
        Assertions.assertEquals(expected, mainPage.getFeedName().getText());
    }

}

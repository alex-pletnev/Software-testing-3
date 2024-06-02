import org.example.ConfProperties;
import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.example.pages.SignInPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SearchTest {

    public static SignInPage signInPage;
    public static WebDriver chromeDriver;
    public static MainPage mainPage;
    public static SearchPage searchPage;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        chromeDriver.get(ConfProperties.getProperty("mainpage"));
        mainPage = new MainPage(chromeDriver);

        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));

        // Прокрутка до элемента
        WebElement singInButton = mainPage.getSingInButton();
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView(true);", singInButton);

        // Убедиться, что элемент кликабелен и не перекрыт
        wait.until(ExpectedConditions.elementToBeClickable(singInButton));

        // Использование JavaScript для клика
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].click();", singInButton);

        signInPage = new SignInPage(chromeDriver);

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOf(signInPage.getEmailInputField()));
        emailInput.click();
        emailInput.sendKeys(ConfProperties.getProperty("auth_email"));

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOf(signInPage.getPasswordInputField()));
        passwordInput.sendKeys(ConfProperties.getProperty("auth_password"));

        WebElement finishSignInButton = wait.until(ExpectedConditions.elementToBeClickable(signInPage.getFinishSingInButton()));
        finishSignInButton.click();
    }

    @AfterAll
    public static void close() throws InterruptedException {
        Thread.sleep(2000);
        chromeDriver.close();
    }

    @DisplayName("Test search")
    @Test
    public void testAuthForm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        Thread.sleep(2000);

        Actions actions = new Actions(chromeDriver);
        System.out.println("111");
        actions.sendKeys("/").perform();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOf(mainPage.getSearchInput()));
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));


        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].value='alex-pletnev/software-testing';", searchInput);

        // Нажатие клавиши ENTER
        searchInput.sendKeys(Keys.ENTER);

        searchPage = new SearchPage(chromeDriver);
        WebElement starButton = wait.until(ExpectedConditions.elementToBeClickable(searchPage.getStarButton()));
        wait.until(ExpectedConditions.elementToBeClickable(starButton));

        Assertions.assertDoesNotThrow(() -> starButton.click());
    }

}

import org.example.ConfProperties;
import org.example.pages.FirstRepositoryPage;
import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.example.pages.SignInPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

public class CommitTest {
    public static SignInPage signInPage;
    public static WebDriver chromeDriver;
    public static MainPage mainPage;
    public static FirstRepositoryPage firstRepositoryPage;

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

    @DisplayName("Test commit")
    @Test
    public void testCommit() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));

        WebElement gotoFirstRepo = wait.until(ExpectedConditions.visibilityOf(mainPage.getGotoFirstRepo()));
        wait.until(ExpectedConditions.elementToBeClickable(gotoFirstRepo));
        gotoFirstRepo.click();

        firstRepositoryPage = new FirstRepositoryPage(chromeDriver);

        WebElement editButton = wait.until(ExpectedConditions.visibilityOf(firstRepositoryPage.getEditButton()));
        wait.until(ExpectedConditions.elementToBeClickable(editButton));


        editButton.click();

        WebElement commitMessageInput = wait.until(ExpectedConditions.visibilityOf(firstRepositoryPage.getCommitMessageInput()));
        wait.until(ExpectedConditions.elementToBeClickable(commitMessageInput));

        commitMessageInput.click();
        commitMessageInput.click();
        commitMessageInput.clear();
        commitMessageInput.sendKeys(UUID.randomUUID().toString());

        firstRepositoryPage.getCommitButton().click();
        firstRepositoryPage.getCommitChangesButton().click();


    }
}


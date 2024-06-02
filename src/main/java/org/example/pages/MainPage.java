package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://github.com/
@Getter
public class MainPage {

    @FindBy(xpath = "//a[contains(@class, 'p-2')]")
    private WebElement singInButton;

    @FindBy(xpath = "//button[contains(@aria-label, 'navigation')]")
    private WebElement navigationButton;

    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div/div[2]/div[3]/deferred-side-panel/include-fragment/user-drawer-side-panel/button/span/span")
    private WebElement avatarButton;

    @FindBy(xpath = "//a[contains(@data-analytics-event, 'navigation\",\"action\":\"PROFILE\",\"label\":null}')]")
    private WebElement profileButton;
    @FindBy(xpath = "//h2[@data-target='feed-container.feedTitle']")
    private WebElement feedName;

    @FindBy(xpath = "//button[@data-hotkey='s,/']")
    private WebElement searchButton;

    @FindBy(xpath = "/html/body/div[1]/div[6]/div/div/aside/div/div/loading-context/div/div[1]/div/ul/li/div/div/a")
    private WebElement gotoFirstRepo;

    //alex-pletnev/software-testing
    @FindBy(xpath = "//*[@id='query-builder-test']")
    private WebElement searchInput;

    public WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }


}

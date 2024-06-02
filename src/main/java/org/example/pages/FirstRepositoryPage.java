package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://github.com/BaldejHub/FirstRepo
@Getter
public class FirstRepositoryPage {

    @FindBy(xpath = "//button[@title='Edit file']")
    private WebElement editButton;

    //https://github.com/BaldejHub/FirstRepo/edit/main/README.md
    @FindBy(xpath = "//div[@dir='auto'][.//br]")
    private WebElement readMyEditField;

    @FindBy(xpath = "//button[@data-hotkey='Mod+s']")
    private WebElement commitButton;

    @FindBy(xpath = "/html/body/div[1]/div[6]/div/main/turbo-frame/div/react-app/div/div/div[1]/div/div/div[2]/div[2]/div/div[3]/div[2]/div/div[2]/file-attachment/div/div/div[2]/div[2]/div[2]")
    private WebElement commitMessageInput;

    @FindBy(xpath = "//button[contains(@class, 'gFhByl')]")
    private WebElement commitChangesButton;

    public WebDriver webDriver;

    public FirstRepositoryPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }
}

package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://github.com/BaldejHub
@Getter
public class ProfilePage {

    @FindBy(xpath = "//li[@class='d-inline-flex'][.//*[@id='repositories-tab']]")
    private WebElement repositoriesButton;

    @FindBy(xpath = "//a[@href='/BaldejHub/FirstRepo']")
    private WebElement goToFirstRepositoryPage;


    public WebDriver webDriver;

    public ProfilePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }


}
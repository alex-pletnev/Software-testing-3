package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://github.com/login
@Getter
public class SignInPage {

    @FindBy(xpath = "//input[contains(@id, 'login')]")
    private WebElement emailInputField;

    @FindBy(xpath = "//input[contains(@id, 'password')]")
    private WebElement passwordInputField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement finishSingInButton;

    public WebDriver webDriver;

    public SignInPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

}

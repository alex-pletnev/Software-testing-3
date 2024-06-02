package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://github.com/search?q=alex-pletnev%2Fsoftware-testing&type=repositories
@Getter
public class SearchPage {

    @FindBy(xpath = "/html/body/div[1]/div[6]/main/react-app/div/div/div[1]/div/div/div[2]/div[2]/div/div[1]/div[4]/div/div/div/div/div[2]/div/button")
    private WebElement starButton;


    public WebDriver webDriver;

    public SearchPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }
}

package cnn.core.cnn_www;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CnnMainPage {

    public WebDriver driver;

    public CnnMainPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='search-button']")
    private WebElement findButton;

    @FindBy(xpath = "//input[@id='search-input-field']")
    private WebElement inputField;

    @FindBy(xpath = "//button[@id='submit-button']")
    private WebElement submitButton;


    public SearchResultPage searchByText(String text)  {
        findButton.click();
        inputField.sendKeys(text);
        submitButton.click();
        return new SearchResultPage(driver);
    }

}

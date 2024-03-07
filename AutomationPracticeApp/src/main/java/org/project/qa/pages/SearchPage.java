package org.project.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;

    @FindBy(linkText = "HP LP3065")
    private WebElement validHPProduct;

    @FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
    private WebElement noProductMatchError;
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public boolean verifyValidProductIsDisplayed(){
        boolean validHPProductDisplayed = validHPProduct.isDisplayed();
        return validHPProductDisplayed;
    }

    public String verifyNoProductMatchingText(){
        String productMatchErrorText = noProductMatchError.getText();
        return productMatchErrorText;
    }
}

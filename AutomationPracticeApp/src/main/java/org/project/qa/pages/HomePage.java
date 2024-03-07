package org.project.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    //Objects
    @FindBy(xpath = "//span[normalize-space()='My Account']")
   private WebElement myAccountDropMenu;

    @FindBy(linkText = "Login")
    private WebElement loginOption;

    @FindBy(linkText = "Register")
    private WebElement registerOption;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchBoxField;
    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchButton;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Actions
    public void clickOnMyAccountDropMenu(){
        myAccountDropMenu.click();
    }
    public void selectLoginOption(){
        loginOption.click();
    }
    public void selectRegisterOption(){
        registerOption.click();
    }

    public void enterProductInSearchBoxField(String product){
        searchBoxField.sendKeys(product);
    }
    public SearchPage clickOnSearchButton(){
        searchButton.click();
        return PageFactory.initElements(driver, SearchPage.class);
    }
}

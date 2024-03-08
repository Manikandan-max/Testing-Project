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

    public LoginPage navigateToLoginPage(){
        myAccountDropMenu.click();
        loginOption.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public LoginPage selectLoginOption(){

        loginOption.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public RegisterPage selectRegisterOption(){

        registerOption.click();
        return PageFactory.initElements(driver, RegisterPage.class);
    }
    public RegisterPage navigateToRegisterPage(){
        myAccountDropMenu.click();
        registerOption.click();
        return PageFactory.initElements(driver, RegisterPage.class);
    }

    public void enterProductInSearchBoxField(String product){

        searchBoxField.sendKeys(product);
    }
    public SearchPage clickOnSearchButton(){
        searchButton.click();
        return PageFactory.initElements(driver, SearchPage.class);
    }
    public SearchPage searchForAProduct(String product){
        enterProductInSearchBoxField(product);
        searchButton.click();
        return PageFactory.initElements(driver, SearchPage.class);
    }
}

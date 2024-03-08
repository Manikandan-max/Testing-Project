package org.project.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    @FindBy(id = "input-email")
    private WebElement emailAddress;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement emailOrPasswordNotMatchingWarning;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterEmailAddress(String emailText){
        emailAddress.sendKeys(emailText);
    }
    public void enterPassword(String passwordText){
        passwordField.sendKeys(passwordText);
    }
    public AccountPage clickOnLoginButton(){
        loginButton.click();
        return PageFactory.initElements(driver, AccountPage.class);
    }
    public String retrieveWarningMessageText(){
        String warningText = emailOrPasswordNotMatchingWarning.getText();
        return warningText;

    }
    public AccountPage doLogin(String emailText, String passwordText){
        emailAddress.sendKeys(emailText);
        passwordField.sendKeys(passwordText);
        loginButton.click();
        return PageFactory.initElements(driver, AccountPage.class);

    }
}

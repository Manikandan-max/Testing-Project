package org.project.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@id='input-firstname']")
    private WebElement firstName;

    @FindBy(id = "input-lastname")
    private WebElement lastName;

    @FindBy(id = "input-email")
    private WebElement emailId;

    @FindBy(id = "input-telephone")
    private WebElement telephone;
    @FindBy(id = "input-password")
    private WebElement password;
    @FindBy(xpath = "//input[@id='input-confirm']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement privacyPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//label[normalize-space()='Yes']")
    private WebElement subscribeNewsOption;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement duplicateEmailWarning;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement privacyPolicyWarning;
    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div[@class='text-danger']")
    private WebElement firstNameWarning;
    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div[@class='text-danger']")
    private WebElement lastNameWarning;
    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div[@class='text-danger']")
    private WebElement emailWarning;
    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div[@class='text-danger']")
    private WebElement telephoneWarning;
    @FindBy(xpath = "//input[@id='input-password']/following-sibling::div[@class='text-danger']")
    private WebElement passwordWarning;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void doRegisterAccountWithMandatoryFields(String firstname,String lastname,String pwd,String email,String phone){
        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        emailId.sendKeys(email);
        telephone.sendKeys(phone);
        password.sendKeys(pwd);
        confirmPassword.sendKeys(pwd);
        privacyPolicy.click();
        continueButton.click();

    }
    public void doRegisterAccount(String firstname,String lastname,String pwd,String email,String phone){
        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        emailId.sendKeys(email);
        telephone.sendKeys(phone);
        password.sendKeys(pwd);
        confirmPassword.sendKeys(pwd);
        subscribeNewsOption.click();
        privacyPolicy.click();
        continueButton.click();

    }
    public void enterFirstNameField(String firstNameText){
        firstName.sendKeys(firstNameText);
    }
    public void enterLastNameField(String lastNameText){
        lastName.sendKeys(lastNameText);
    }
    public void enterEmailId(String emailAddress){
        emailId.sendKeys(emailAddress);
    }
    public void enterPassword(String pwd){
        password.sendKeys(pwd);
    }
    public void enterConfirmPassword(String confirmPwd){
        confirmPassword.sendKeys(confirmPwd);
    }
    public void enterTelephoneField(String phone){
        telephone.sendKeys(phone);
    }
    public void clickOnPrivacyPolicy(){
        privacyPolicy.click();
    }
    public void clickOnContinueButton(){
        continueButton.click();
    }
    public void clickOnSubscribeNewsLetter(){
        subscribeNewsOption.click();
    }

    public String retrieveduplicateEmailWarningText(){
        String duplicateEmailWarningText = duplicateEmailWarning.getText();
        return duplicateEmailWarningText;
    }
    public String retrievePrivatePolicyWarningText(){
        String privacyPolicyWarningText = privacyPolicyWarning.getText();
        return privacyPolicyWarningText;
    }
    public String retrieveFirstNameWarningText(){
        String firstNameWarningText = firstNameWarning.getText();
        return firstNameWarningText;
    }
    public String retrieveLastNameWarningText(){
        String lastNameWarningText = lastNameWarning.getText();
        return lastNameWarningText;
    }
    public String retrieveEmailWarningText(){
        String emailWarningText = emailWarning.getText();
        return emailWarningText;
    }
    public String retrievePasswordWarningText(){
        String passwordWarningText = passwordWarning.getText();
        return passwordWarningText;
    }
    public String retrieveTelephoneWarning(){
        String telephoneWarningText = telephoneWarning.getText();
        return telephoneWarningText;
    }
}

package org.project.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.project.qa.base.BaseClass;
import org.project.qa.pages.AccountPage;
import org.project.qa.pages.HomePage;
import org.project.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.project.qa.utils.ElementUtils.generateEmailWithTimeStamp;

public class Login extends BaseClass {

    public WebDriver driver;
    LoginPage  loginPage;

    public Login() {
        super();
    }

    @BeforeMethod
    public void openURL() {
        driver = invokeBrowser(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.navigateToLoginPage();
    }

    @Test(priority = 1, dataProvider = "getData")
    public void verifyWithValidCredentials(String email, String password) {
        AccountPage accountPage= loginPage.doLogin(email,password);
        Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInfo());
    }

    @Test(priority = 2)
    public void verifyWithInvalidCredentials() {
        loginPage.doLogin(generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
        String errorText = loginPage.retrieveWarningMessageText();
        System.out.println("Error Message: "+errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        loginPage.doLogin(generateEmailWithTimeStamp(),prop.getProperty("validPassword"));
        String errorText = loginPage.retrieveWarningMessageText();
        System.out.println("Error Message: "+errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {
        loginPage.doLogin(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
        String errorText = loginPage.retrieveWarningMessageText();
        System.out.println("Error Message: "+errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials() {

        loginPage.clickOnLoginButton();
        String errorText = loginPage.retrieveWarningMessageText();
        System.out.println("Error Message: "+errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

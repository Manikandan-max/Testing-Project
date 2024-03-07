package org.project.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.project.qa.base.BaseClass;
import org.project.qa.pages.AccountPage;
import org.project.qa.pages.HomePage;
import org.project.qa.pages.LoginPage;
import org.project.qa.utils.ReadExcel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.project.qa.utils.ElementUtils.generateEmailWithTimeStamp;

public class Login extends BaseClass {

    public WebDriver driver;

    public Login() {
        super();
    }

    @BeforeMethod
    public void openURL() {
        driver = invokeBrowser(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        homePage.selectLoginOption();
    }

    @Test(priority = 1, dataProvider = "getData")
    public void verifyWithValidCredentials(String email, String password) {

        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAddress(email);
        loginPage.enterPassword(password);
        AccountPage accountPage= loginPage.clickOnLoginButton();
        Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInfo());
    }

    @Test(priority = 2)
    public void verifyWithInvalidCredentials() {

        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAddress(generateEmailWithTimeStamp());
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.clickOnLoginButton();
        String errorText = loginPage.retrieveWarningMessageText();
        System.out.println(errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAddress(generateEmailWithTimeStamp());
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickOnLoginButton();
        String errorText = loginPage.retrieveWarningMessageText();
        System.out.println(errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAddress(prop.getProperty("validEmail"));
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.clickOnLoginButton();
        String errorText = loginPage.retrieveWarningMessageText();
        System.out.println(errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials() {

        LoginPage loginPage=new LoginPage(driver);
        loginPage.clickOnLoginButton();
        String errorText = loginPage.retrieveWarningMessageText();
        System.out.println(errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

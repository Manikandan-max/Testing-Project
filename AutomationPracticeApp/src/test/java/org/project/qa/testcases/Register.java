package org.project.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.project.qa.base.BaseClass;
import org.project.qa.pages.AccountSuccessPage;
import org.project.qa.pages.HomePage;
import org.project.qa.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.project.qa.utils.ElementUtils.generateEmailWithTimeStamp;

public class Register extends BaseClass {
    public WebDriver driver;

    public Register() {
        super();
    }

    @BeforeMethod
    public void openUrl() {
        driver = invokeBrowser(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        homePage.selectRegisterOption();

    }

    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryField() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.doRegisterAccountWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), dataProp.getProperty("password"), generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"));

        AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        String actualHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
        System.out.println(actualHeading);
        Assert.assertEquals(actualHeading, dataProp.getProperty("successMessage"), "Account Success Page is not created");

    }

    @Test(priority = 2)
    public void verifyRegisteringAnAccountByProvidingAllFields() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.doRegisterAccountWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), dataProp.getProperty("password"), generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"));


        AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        String actualHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
        System.out.println(actualHeading);
        Assert.assertEquals(actualHeading, dataProp.getProperty("successMessage"), "Account Success Page is not created");

    }

    @Test(priority = 3)
    public void verifyRegisteringAnAccountByProvidingExistingEmail() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.doRegisterAccountWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), dataProp.getProperty("password"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"));

        String actualError = registerPage.retrieveduplicateEmailWarningText();
        System.out.println(actualError);
        Assert.assertEquals(actualError, dataProp.getProperty("duplicateEmailWarning"), "Existing Email ID given");

    }

    @Test(priority = 4)
    public void verifyRegisteringAnAccountWithoutFillingAnyDetails() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickOnContinueButton();
        String privacyPolicyWarnings = registerPage.retrievePrivatePolicyWarningText();
        System.out.println(privacyPolicyWarnings);
        Assert.assertEquals(privacyPolicyWarnings, dataProp.getProperty("privacyPolicyWarning"));
        String firstNameWarning = registerPage.retrieveFirstNameWarningText();
        System.out.println(firstNameWarning);
        Assert.assertEquals(firstNameWarning, dataProp.getProperty("firstNameWarning"));
        String lastNameWarning = registerPage.retrieveLastNameWarningText();
        System.out.println(lastNameWarning);
        Assert.assertEquals(lastNameWarning, dataProp.getProperty("lastNameWarning"));
        String emailWarning = registerPage.retrieveEmailWarningText();
        System.out.println(emailWarning);
        Assert.assertEquals(emailWarning, dataProp.getProperty("emailWarning"));
        String telephoneWarning = registerPage.retrieveTelephoneWarning();
        System.out.println(telephoneWarning);
        Assert.assertEquals(telephoneWarning, dataProp.getProperty("telephoneWarning"));
        String passwordWarning = registerPage.retrievePasswordWarningText();
        System.out.println(passwordWarning);
        Assert.assertEquals(passwordWarning, dataProp.getProperty("passwordWarning"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

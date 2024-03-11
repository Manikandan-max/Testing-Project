package org.project.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.project.qa.base.BaseClass;
import org.project.qa.pages.AccountSuccessPage;
import org.project.qa.pages.HomePage;
import org.project.qa.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.project.qa.utils.ElementUtils.generateEmailWithTimeStamp;

public class Register extends BaseClass {
    public WebDriver driver;
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;
    public Register() {
        super();
    }

    @BeforeMethod
    public void openUrl() {
        driver = invokeBrowser(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        registerPage = homePage.navigateToRegisterPage();

    }

    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryField() {
        accountSuccessPage=registerPage.doRegisterAccountWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), dataProp.getProperty("password"), generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"));
        String actualHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
        System.out.println(actualHeading);
        Assert.assertEquals(actualHeading, dataProp.getProperty("successMessage"), "Account Success Page is not created");

    }

    @Test(priority = 2)
    public void verifyRegisteringAnAccountByProvidingAllFields() {
        accountSuccessPage = registerPage.doRegisterAccount(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), dataProp.getProperty("password"), generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"));
        String actualHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
        System.out.println(actualHeading);
        Assert.assertEquals(actualHeading, dataProp.getProperty("successMessage"), "Account Success Page is not created");

    }

    @Test(priority = 3)
    public void verifyRegisteringAnAccountByProvidingExistingEmail() {
        registerPage.doRegisterAccountWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), dataProp.getProperty("password"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"));

        String actualError = registerPage.retrieveduplicateEmailWarningText();
        System.out.println(actualError);
        Assert.assertEquals(actualError, dataProp.getProperty("duplicateEmailWarning"), "Existing Email ID given");

    }

    @Test(priority = 4)
    public void verifyRegisteringAnAccountWithoutFillingAnyDetails() {
        List<String> actualWarnings = registerPage.registerAccountWithoutFillingDetailsAndRetrieveWarnings();

        List<String> expectedWarnings = Arrays.asList(
                dataProp.getProperty("firstNameWarning"),
                dataProp.getProperty("lastNameWarning"),
                dataProp.getProperty("emailWarning"),
                dataProp.getProperty("privacyPolicyWarning"),
                dataProp.getProperty("telephoneWarning"),
                dataProp.getProperty("passwordWarning")
        );

        Assert.assertTrue(actualWarnings.containsAll(expectedWarnings), "All warning messages not displayed");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

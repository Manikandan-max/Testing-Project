package org.project.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.project.qa.base.BaseClass;
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
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("(//a[normalize-space()='Register'])[1]")).click();

    }
    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryField(){
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(dataProp.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(generateEmailWithTimeStamp());
        driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
        driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("password"));
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(dataProp.getProperty("password"));
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        System.out.println(actualHeading);
        Assert.assertEquals(actualHeading,dataProp.getProperty("successMessage"),"Account Success Page is not created");

    }

    @Test(priority = 2)
    public void verifyRegisteringAnAccountByProvidingAllFields(){
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(dataProp.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(generateEmailWithTimeStamp());
        driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
        driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("password"));
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(dataProp.getProperty("password"));
        driver.findElement(By.xpath("//label[normalize-space()='Yes']")).click();
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        System.out.println(actualHeading);
        Assert.assertEquals(actualHeading,dataProp.getProperty("successMessage"),"Account Success Page is not created");

    }
    @Test(priority = 3)
    public void verifyRegisteringAnAccountByProvidingExistingEmail(){
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(dataProp.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
        driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
        driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("password"));
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(dataProp.getProperty("password"));
        driver.findElement(By.xpath("//label[normalize-space()='Yes']")).click();
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualError=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(actualError);
        Assert.assertEquals(actualError,dataProp.getProperty("duplicateEmailWarning"),"Existing Email ID given");

    }
    @Test(priority = 4)
    public void verifyRegisteringAnAccountWithoutFillingAnyDetails(){
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String privacyPolicyWarnings=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(privacyPolicyWarnings);
        Assert.assertEquals(privacyPolicyWarnings,dataProp.getProperty("privacyPolicyWarning"));
        String firstNameWarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div[@class='text-danger']")).getText();
        System.out.println(firstNameWarning);
        Assert.assertEquals(firstNameWarning,dataProp.getProperty("firstNameWarning"));
        String lastNameWarning=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div[@class='text-danger']")).getText();
        System.out.println(lastNameWarning);
        Assert.assertEquals(lastNameWarning,dataProp.getProperty("lastNameWarning"));
        String emailWarning=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div[@class='text-danger']")).getText();
        System.out.println(emailWarning);
        Assert.assertEquals(emailWarning,dataProp.getProperty("emailWarning"));
        String telephoneWarning=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div[@class='text-danger']")).getText();
        System.out.println(telephoneWarning);
        Assert.assertEquals(telephoneWarning,dataProp.getProperty("telephoneWarning"));
        String passwordWarning=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div[@class='text-danger']")).getText();
        System.out.println(passwordWarning);
        Assert.assertEquals(passwordWarning,dataProp.getProperty("passwordWarning"));

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

package org.project.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.project.qa.base.BaseClass;
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
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

    }

    @Test(priority = 1,dataProvider = "getData")
    public void verifyWithValidCredentials(String email,String password) {
//        driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
        driver.findElement(By.id("input-email")).sendKeys(email);
//        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
    }

    @Test(priority = 2)
    public void verifyWithInvalidCredentials() {

        driver.findElement(By.id("input-email")).sendKeys(generateEmailWithTimeStamp());
        driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String errorText = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        driver.findElement(By.id("input-email")).sendKeys( generateEmailWithTimeStamp());
        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String errorText = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));

    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {
        driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
        driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String errorText = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials() {

//        driver.findElement(By.id("input-email")).sendKeys("");
//        driver.findElement(By.id("input-password")).sendKeys("");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String errorText = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(errorText);
        Assert.assertEquals(errorText, dataProp.getProperty("emailPasswordNoMatch"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

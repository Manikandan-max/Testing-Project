package org.project.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Login {

    @Test(priority = 1)
    public void verifyWithValidCredentials(){

        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys("tpractice427@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Testing0@");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
        driver.quit();


    }
    @Test(priority = 2)
    public void verifyWithInvalidCredentials() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys("tpractices427"+generateTimeStamp()+"@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Tessting0@");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String errorText = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(errorText);
        Assert.assertEquals(errorText,"Warning: No match for E-Mail Address and/or Password.");
        driver.quit();
    }
    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys("tpractices427"+generateTimeStamp()+"@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Testing0@");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String errorText = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(errorText);
        Assert.assertEquals(errorText,"Warning: No match for E-Mail Address and/or Password.");
        driver.quit();
    }
    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys("tpractices427@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Tessting0@");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String errorText = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(errorText);
        Assert.assertEquals(errorText,"Warning: No match for E-Mail Address and/or Password.");
        driver.quit();
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys(" ");
        driver.findElement(By.id("input-password")).sendKeys(" ");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String errorText = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        System.out.println(errorText);
        Assert.assertEquals(errorText,"Warning: No match for E-Mail Address and/or Password.");
        driver.quit();

    }


    public String generateTimeStamp(){
        Date date=new Date();
        String s = date.toString().replaceAll(" ", "_").replaceAll(":","_").trim();
        return s;
    }
}

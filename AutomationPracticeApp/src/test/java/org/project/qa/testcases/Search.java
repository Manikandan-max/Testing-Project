package org.project.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.project.qa.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends BaseClass {

    WebDriver driver;

    public Search() {
        super();
    }

    @BeforeMethod
    public void openUrl(){
        driver=invokeBrowser(prop.getProperty("browser"));

    }
    @Test(priority = 1)
    public void verifySearchingWithAnExistingProductName(){

        driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(dataProp.getProperty("validProduct"));
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());

    }

    @Test(priority = 2)
    public void verifySearchingWithNonExistingProductName(){
        driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(dataProp.getProperty("invalidProduct"));
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::h2")).isDisplayed());
        String actualMessage=driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();        System.out.println(actualMessage);
        Assert.assertEquals(actualMessage,dataProp.getProperty("noProductSearchResults"),"Product is found!");

    }
    @Test(priority = 3)
    public void verifySearchingWithoutProductName(){
      //  driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("");
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::h2")).isDisplayed());
        String actualMessage=driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
        System.out.println(actualMessage);
        Assert.assertEquals(actualMessage,dataProp.getProperty("noProductSearchResults"),"Product is not  found!");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

package org.project.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.project.qa.base.BaseClass;
import org.project.qa.pages.HomePage;
import org.project.qa.pages.SearchPage;
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
    public void openUrl() {
        driver = invokeBrowser(prop.getProperty("browser"));

    }

    @Test(priority = 1)
    public void verifySearchingWithAnExistingProductName() {
        HomePage homePage = new HomePage(driver);

        homePage.enterProductInSearchBoxField(dataProp.getProperty("validProduct"));
        SearchPage searchPage = homePage.clickOnSearchButton();
        Assert.assertTrue(searchPage.verifyValidProductIsDisplayed());

    }

    @Test(priority = 2)
    public void verifySearchingWithNonExistingProductName() {
        HomePage homePage = new HomePage(driver);
        homePage.enterProductInSearchBoxField(dataProp.getProperty("invalidProduct"));
        SearchPage searchPage = homePage.clickOnSearchButton();
        String actualMessage = searchPage.verifyNoProductMatchingText();
        System.out.println(actualMessage);
        Assert.assertEquals(actualMessage, dataProp.getProperty("noProductSearchResults"), "Product is found!");

    }

    @Test(priority = 3)
    public void verifySearchingWithoutProductName() {
        HomePage homePage=new HomePage(driver);
        SearchPage searchPage = homePage.clickOnSearchButton();
        String actualMessage = searchPage.verifyNoProductMatchingText();
        System.out.println(actualMessage);
        Assert.assertEquals(actualMessage, dataProp.getProperty("noProductSearchResults"), "Product is found!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

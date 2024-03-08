package org.project.qa.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.project.qa.utils.ElementUtils;
import org.project.qa.utils.ReadExcel;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static java.sql.DriverManager.getDriver;

public class BaseClass {

    public  WebDriver driver;
   public Properties prop;
   public Properties dataProp;
public BaseClass(){
    prop=new Properties();
    File propFile=new File(System.getProperty("user.dir")+"/src/main/java/org/project/qa/config/config.properties");
    dataProp=new Properties();
    File file=new File(System.getProperty("user.dir")+"/src/main/java/org/project/qa/testData/testData.properties");
    try (FileInputStream fis = new FileInputStream(file)) {
        dataProp.load(fis);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    try {
        FileInputStream readFile=new FileInputStream(propFile);
        prop.load(readFile);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
        public WebDriver invokeBrowser(String browserName) {
            switch (browserName.toLowerCase()){
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver=new EdgeDriver();
                    break;
                case "firefox":
                    driver=new FirefoxDriver();
                    break;
                case "ie":
                    driver=new InternetExplorerDriver();
                    break;
                case "safari":
                    driver=new SafariDriver();
                    break;
                default:
                    System.out.println("Please enter the valid BrowserName");

            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ElementUtils.IMPLICITWAIT_WAIT_TIME));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ElementUtils.PAGE_LOAD_TIME));
            driver.get(prop.getProperty("url"));
            return driver;
    }
    @DataProvider(name = "getData")
    public Object[][] sendData(){
        Object[][] data= ReadExcel.getTestData(dataProp.getProperty("sheetName"));
        return data;

    }

    @BeforeSuite
    public void cleanScreenshotFolder() {
        File screenshotFolder = new File("screenshots");
        if (screenshotFolder.exists() && screenshotFolder.isDirectory()) {
            File[] files = screenshotFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                Path destinationPath = Paths.get("screenshots", result.getName() + "_" + timestamp + ".png");
                FileUtils.copyFile(sourceFile, destinationPath.toFile());
                System.out.println("Screenshot saved: " + destinationPath);
            } catch (IOException e) {
                System.out.println("Failed to take screenshot: " + e.getMessage());
            }
        }
    }

}

package org.project.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.project.qa.utils.ElementUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public  WebDriver driver;
   public Properties prop;
public BaseClass(){
    prop=new Properties();
    File propFile=new File(System.getProperty("user.dir")+"/src/main/java/org/project/qa/config/config.properties");
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

}

package org.project.qa.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ElementUtils {
    public static final int IMPLICITWAIT_WAIT_TIME=15;
    public static final int PAGE_LOAD_TIME=10;
    public static String generateEmailWithTimeStamp() {
        Date date = new Date();
        String s = date.toString().replaceAll(" ", "_").replaceAll(":", "_").trim();
        return "testingpractice"+s+"@gmail.com";
    }

    public static String takeScreenShotsOnFailure(WebDriver driver, String testName){
        TakesScreenshot screenshots = (TakesScreenshot) driver;
        File srcFile = screenshots.getScreenshotAs(OutputType.FILE);
        String destinationPath=System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
        try {
            FileUtils.copyFile(srcFile,new File(destinationPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destinationPath;
    }
}

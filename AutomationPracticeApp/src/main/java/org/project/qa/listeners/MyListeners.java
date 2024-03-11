package org.project.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.project.qa.utils.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {
    ExtentReports report;
    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {

        report = ExtentManager.generateReport();

    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName= result.getName();
        test = report.createTest(testName);
        test.log(Status.INFO,testName+" started executing");


    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName= result.getName();
        test = report.createTest(testName);
        test.log(Status.PASS,testName+" got successfully executed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        WebDriver driver;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        TakesScreenshot screenshots = (TakesScreenshot) driver;
        File srcFile = screenshots.getScreenshotAs(OutputType.FILE);
        String destinationPath=System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
        try {
            FileUtils.copyFile(srcFile,new File(destinationPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(destinationPath);
        test.log(Status.INFO,result.getThrowable());
        test.log(Status.FAIL,testName+" got failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        String testName=result.getName();
        test.log(Status.INFO,result.getThrowable());
        test.log(Status.SKIP,testName+" got skipped");

    }


    @Override
    public void onFinish(ITestContext context) {
        report.flush();


    }
}

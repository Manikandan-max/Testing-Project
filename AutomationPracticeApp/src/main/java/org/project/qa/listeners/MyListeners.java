package org.project.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.project.qa.utils.ElementUtils;
import org.project.qa.utils.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
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

        test = report.createTest(result.getName());
        test.log(Status.INFO,result.getName()+" started executing");


    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = report.createTest(result.getName());
        test.log(Status.PASS,result.getName()+" got successfully executed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String destinationFilePath = ElementUtils.takeScreenShotsOnFailure(driver, result.getName());
        test.addScreenCaptureFromPath(destinationFilePath);
        test.log(Status.INFO,result.getThrowable());
        test.log(Status.FAIL,result.getName()+" got failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.INFO,result.getThrowable());
        test.log(Status.SKIP,result.getName()+" got skipped");

    }


    @Override
    public void onFinish(ITestContext context) {
        report.flush();
        String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\extent-reports\\extent-report.html";
        File extentReport=new File(pathOfExtentReport);
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

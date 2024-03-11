package org.project.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExtentManager {
    public static ExtentReports generateReport(){

        ExtentReports reports=new ExtentReports();
        File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\extent-reports\\extent-report.html");
        ExtentSparkReporter spark=new ExtentSparkReporter(extentReportFile);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Tutorial Ninja Document");
        spark.config().setReportName("Test Automation Results");
        spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
        reports.attachReporter(spark);
        Properties properties=new Properties();
        File configPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\project\\qa\\config\\config.properties");
        try {
            FileInputStream fis=new FileInputStream(configPropFile);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reports.setSystemInfo("Application URL", properties.getProperty("url"));
        reports.setSystemInfo("Browser Name", properties.getProperty("browser"));
        reports.setSystemInfo("Operating system", System.getProperty("os.name"));
        reports.setSystemInfo("UserName",System.getProperty("user.name"));
        reports.setSystemInfo("Java Version",System.getProperty("java.version"));

        return reports;



    }
}

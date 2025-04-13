package com.central.framework.reportutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Initialize the report
    public static void initReports(String reportPath, String testName) {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle(testName);
            spark.config().setReportName(testName);
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Framework", "TestNG");
            extent.setSystemInfo("Environment","QA");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
        }
    }

    // Flush the report
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Create test in report
    public static void createTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
    }

    // Get the current thread's test
    public static ExtentTest getTest() {
        if (extentTest.get() == null) {
            throw new IllegalStateException("ExtentTest is not initialized for this thread.");
        }
        return extentTest.get();
    }
    // Remove test from thread-local (optional cleanup)
    public static void unload() {
        extentTest.remove();
    }

    public static void addAuthor(String author) {
        getTest().assignAuthor(author);
    }

    public static void addCategory(String category) {
        getTest().assignCategory(category);
    }

    public static void addScreenshot(String base64Screenshot, String title) {
        getTest().addScreenCaptureFromBase64String(base64Screenshot, title);
    }

    public static void addScreenshotFromPath(String path, String title) {
        getTest().addScreenCaptureFromPath(path, title);
    }
}

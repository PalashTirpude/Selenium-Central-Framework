package com.central.framework.assertutil;

import com.central.framework.reportutils.ExtentReportManager;
import com.aventstack.extentreports.Status;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

public class CustomAssert {

    private static final ThreadLocal<SoftAssert> softAssertThreadLocal = ThreadLocal.withInitial(SoftAssert::new);

    // ===== HARD ASSERT METHODS ===== //

    public static void assertEqualsHard(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected);
            ExtentReportManager.getTest().log(Status.PASS,
                    message + " | ✅ Expected: " + expected + " | Actual: " + actual);
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.FAIL,
                    message + " | ❌ Expected: " + expected + " | Actual: " + actual + " | Exception: " + e.getMessage());
            throw e; // rethrow to fail the test immediately
        }
    }

    public static void assertTrueHard(boolean condition, String message) {
        try {
            Assert.assertTrue(condition);
            ExtentReportManager.getTest().log(Status.PASS, message + " | ✅ Condition is true");
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.FAIL, message + " | ❌ Condition is false | Exception: " + e.getMessage());
            throw e;
        }
    }

    // ===== SOFT ASSERT METHODS ===== //

    public static void assertEqualsSoft(Object actual, Object expected, String message) {
        try {
            softAssertThreadLocal.get().assertEquals(actual, expected);
            ExtentReportManager.getTest().log(Status.INFO,
                    message + " | 🧪 (Soft) Expected: " + expected + " | Actual: " + actual);
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.WARNING,
                    message + " | ❌ (Soft) Assertion failed | Expected: " + expected + " | Actual: " + actual + " | Exception: " + e.getMessage());
        }
    }

    public static void assertTrueSoft(boolean condition, String message) {
        try {
            softAssertThreadLocal.get().assertTrue(condition);
            ExtentReportManager.getTest().log(Status.INFO, message + " | 🧪 (Soft) Condition is true");
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.WARNING, message + " | ❌ (Soft) Condition failed | Exception: " + e.getMessage());
        }
    }

    // ===== SOFT ASSERT FINALIZER ===== //

    public static void assertAllSoft(String summaryMessage) {
        try {
            softAssertThreadLocal.get().assertAll();
            ExtentReportManager.getTest().log(Status.PASS, summaryMessage + " | ✅ All soft assertions passed.");
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.FAIL, summaryMessage + " | ❌ One or more soft assertions failed: " + e.getMessage());
            throw e;
        } finally {
            softAssertThreadLocal.remove(); // Clean up to avoid memory leaks in parallel tests
        }
    }
}

package com.central.framework.selenium;

import com.central.framework.genericutils.LoadProperties;
import com.central.framework.selenium.enums.Browsers;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

@Slf4j
public class DriverInitializer {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    @Getter
    private static Properties properties;

    public static void initializeWebDriver(String browserName,String filePath) {
        DriverManager driverManager = new DriverManager();
        properties=LoadProperties.fromFile(filePath);
        driverManager.setProperties(properties);
        WebDriver driver = driverManager.getWebDriver(Browsers.valueOf(browserName));
        driverThreadLocal.set(driver);
    }

    public static WebDriver getWebDriver() {
        if (driverThreadLocal.get() == null) {
            log.error("WebDriver instance is null! Did you forget to initialize it?");
            throw new IllegalStateException("WebDriver is not initialized. Call initializeWebDriver() first.");
        }
        return driverThreadLocal.get();
    }

    public static void quitWebDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

}

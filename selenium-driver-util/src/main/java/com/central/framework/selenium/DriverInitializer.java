package com.central.framework.selenium;

import com.central.framework.selenium.enums.Browsers;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class DriverInitializer {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    @Getter
    private static Properties properties;
    private static final String propertyFileName = "driver-config.properties";

    public static void initializeWebDriver(String browserName) {
        DriverManager driverManager = new DriverManager();
        try {
            loadDriverProperty();
        } catch (URISyntaxException | IOException e) {
            log.error("Error loading driver property file", e);
            throw new RuntimeException("Failed to load properties file", e);
        }

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

    private static void loadDriverProperty() throws URISyntaxException, IOException {
        if (properties == null) {
            String propertiesPath = System.getProperty("driver.config.path");
            properties = new Properties();
            File propertyFile;

            if (propertiesPath != null && !propertiesPath.isEmpty()) {
                propertyFile = new File(propertiesPath);
            } else {
                propertyFile = new File(Objects.requireNonNull(
                        Thread.currentThread().getContextClassLoader().getResource(propertyFileName)
                ).toURI());
            }

            properties.load(new FileInputStream(propertyFile));
        }
    }

}

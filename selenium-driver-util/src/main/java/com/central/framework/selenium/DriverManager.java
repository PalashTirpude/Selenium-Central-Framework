package com.central.framework.selenium;


import com.central.framework.selenium.enums.Browsers;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@Setter
@Slf4j
public class DriverManager {
    private Properties properties;

    public WebDriver getWebDriver(Browsers browser) {
        String driverKey = properties.getProperty("driver.%s.system.key".formatted(browser.name().toLowerCase()));
        String driverPath = properties.getProperty("driver.%s.system.value.driver.file.path".formatted(browser.name().toLowerCase()));
        System.setProperty(driverKey,driverPath);

        log.info("driver key : {}", driverKey);
        log.info("driver path : {}", driverPath);

        return switch (browser.name().toLowerCase()) {
            case "chrome" -> new ChromeDriver(chromeOptions());
            case "edge" -> new EdgeDriver(edgeOptions());
            case "firefox" -> new FirefoxDriver(firefoxOptions());
            default -> throw new RuntimeException("Invalid browser selected");
        };
    }

    private ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(getArguments());
        chromeOptions.setExperimentalOption("prefs", getPreferences());
        return chromeOptions;
    }

    private EdgeOptions edgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments(getArguments());
        edgeOptions.setExperimentalOption("prefs", getPreferences());
        return edgeOptions;
    }

    private FirefoxOptions firefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(getArguments());
        FirefoxProfile profile = new FirefoxProfile();
        getPreferences().forEach(profile::setPreference);
        firefoxOptions.setProfile(profile);
        return firefoxOptions;
    }

    private String[] getArguments() {
        return new String[]{"enable-automation-testing",
                "--ignore-certificate-errors",
                "--start-maximized",
                "--disable-application-cache",
                "--disable-restore-session-state",
                "--disable-infobars",
                "--disable-extensions",
                "--disable-gpu",
                "--disable-save-password-bubble",
                "--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/EdgeProfile_" + UUID.randomUUID()
        };
    }

    private Map<String, Object> getPreferences() {
        Map<String, Object> prefs = new HashMap<>();
        File downloadPath = new File("downloads");
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("download.prompt_for_download", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("download.default_directory", downloadPath.getAbsolutePath());
        return prefs;
    }
}

package com.central.framework.genericutils;


import com.central.framework.selenium.DriverInitializer;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

@UtilityClass
public class DriverWait {
    public void waitVisibilityOfElementLocated(By locator, int waitTime) {
        WebDriverWait wait = new WebDriverWait(DriverInitializer.getWebDriver(), Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitElementToBeClickable(By locator, int waitTime) {
        WebDriverWait wait = new WebDriverWait(DriverInitializer.getWebDriver(), Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitFrameToBeAvailableAndSwitchToIt(String locator, int waitTime) {
        WebDriverWait wait = new WebDriverWait(DriverInitializer.getWebDriver(), Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void waitFrameToBeAvailableAndSwitchToIt(By locator, int waitTime) {
        WebDriver driver = DriverInitializer.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
    }

    public void waitAttributeToBe(By locator, String attribute, String value, int waitTime) {
        WebDriverWait wait = new WebDriverWait(DriverInitializer.getWebDriver(), Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.attributeToBe(locator, attribute,value));
    }

    public void waitAlertIsPresent( int waitTime) {
        WebDriverWait wait = new WebDriverWait(DriverInitializer.getWebDriver(), Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.alertIsPresent());
    }


}

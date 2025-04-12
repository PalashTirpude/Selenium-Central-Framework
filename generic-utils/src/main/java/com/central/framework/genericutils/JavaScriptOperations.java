package com.central.framework.genericutils;

import com.central.framework.selenium.DriverInitializer;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


@UtilityClass
public class JavaScriptOperations {
    WebDriver driver= DriverInitializer.getWebDriver();

    // Scroll to a specific driver.findElement(locator)
    public void scrollToElement(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    // Scroll to the bottom of the page
    public void scrollToBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Click an driver.findElement(locator) using JavaScript
    public void clickElement(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    // Send keys to an input field using JavaScript
    public void sendKeysUsingJS(By locator, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value = arguments[1];", driver.findElement(locator), value);
    }

    // Highlight an driver.findElement(locator) (useful for debugging)
    public void highlightElement(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(locator));
    }

    // Retrieve inner text of an driver.findElement(locator)
    public String getInnerText(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].innerText;", driver.findElement(locator));
    }

    // Retrieve page title using JavaScript
    public String getPageTitle() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.title;");
    }

    // Retrieve current URL using JavaScript
    public String getCurrentURL() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.URL;");
    }

    // Refresh page using JavaScript
    public void refreshPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("history.go(0);");
    }

    // Scroll horizontally to an driver.findElement(locator)
    public void scrollHorizontallyToElement(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'nearest', inline: 'center'});", driver.findElement(locator));
    }

    // Hide an driver.findElement(locator) using JavaScript
    public void hideElement(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.display='none';", driver.findElement(locator));
    }

    // Show an driver.findElement(locator) using JavaScript
    public void showElement(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.display='block';", driver.findElement(locator));
    }

    // Change driver.findElement(locator) background color (for debugging)
    public void changeElementBackgroundColor(By locator, String color) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.backgroundColor = arguments[1];", driver.findElement(locator), color);
    }

    // Zoom the webpage
    public void zoomPage(double zoomLevel) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.body.style.zoom = arguments[0] + '%';", zoomLevel);
    }

    // Scroll to specific coordinates
    public void scrollToCoordinates(int x, int y) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
    }

    // Disable an input field
    public void disableInputField(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('disabled', 'true');", driver.findElement(locator));
    }

    // Enable an input field
    public void enableInputField(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(locator));
    }
}

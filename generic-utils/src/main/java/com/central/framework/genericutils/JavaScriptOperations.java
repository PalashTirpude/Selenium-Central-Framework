package com.central.framework.genericutils;

import com.central.framework.selenium.DriverInitializer;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

@UtilityClass
public class JavaScriptOperations {

    private static WebDriver getDriver() {
        return DriverInitializer.getWebDriver();
    }

    private static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    public void scrollToElement(By locator) {
        getJsExecutor().executeScript(
                "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
                getDriver().findElement(locator)
        );
    }

    public void scrollToBottom() {
        getJsExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void clickElement(By locator) {
        getJsExecutor().executeScript("arguments[0].click();", getDriver().findElement(locator));
    }

    public void sendKeysUsingJS(By locator, String value) {
        getJsExecutor().executeScript("arguments[0].value = arguments[1];", getDriver().findElement(locator), value);
    }

    public void highlightElement(By locator) {
        getJsExecutor().executeScript("arguments[0].style.border='3px solid red'", getDriver().findElement(locator));
    }

    public String getInnerText(By locator) {
        return (String) getJsExecutor().executeScript("return arguments[0].innerText;", getDriver().findElement(locator));
    }

    public String getPageTitle() {
        return (String) getJsExecutor().executeScript("return document.title;");
    }

    public String getCurrentURL() {
        return (String) getJsExecutor().executeScript("return document.URL;");
    }

    public void refreshPage() {
        getJsExecutor().executeScript("history.go(0);");
    }

    public void scrollHorizontallyToElement(By locator) {
        getJsExecutor().executeScript(
                "arguments[0].scrollIntoView({block: 'nearest', inline: 'center'});",
                getDriver().findElement(locator)
        );
    }

    public void hideElement(By locator) {
        getJsExecutor().executeScript("arguments[0].style.display='none';", getDriver().findElement(locator));
    }

    public void showElement(By locator) {
        getJsExecutor().executeScript("arguments[0].style.display='block';", getDriver().findElement(locator));
    }

    public void changeElementBackgroundColor(By locator, String color) {
        getJsExecutor().executeScript("arguments[0].style.backgroundColor = arguments[1];", getDriver().findElement(locator), color);
    }

    public void zoomPage(double zoomLevel) {
        getJsExecutor().executeScript("document.body.style.zoom = arguments[0] + '%';", zoomLevel);
    }

    public void scrollToCoordinates(int x, int y) {
        getJsExecutor().executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
    }

    public void disableInputField(By locator) {
        getJsExecutor().executeScript("arguments[0].setAttribute('disabled', 'true');", getDriver().findElement(locator));
    }

    public void enableInputField(By locator) {
        getJsExecutor().executeScript("arguments[0].removeAttribute('disabled');", getDriver().findElement(locator));
    }
}

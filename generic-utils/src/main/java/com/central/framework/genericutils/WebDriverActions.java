package com.central.framework.genericutils;

import com.central.framework.selenium.DriverInitializer;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@UtilityClass
public class WebDriverActions {

    public WebElement findElement(By locator) {
        return DriverInitializer.getWebDriver().findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return DriverInitializer.getWebDriver().findElements(locator);
    }

    public Select selectByIndex(By locator,int index){
        DriverWait.waitVisibilityOfElementLocated(locator,5);
        Select select=new Select(findElement(locator));
        select.selectByIndex(index);
        return select;
    }

    public Select selectByValue(By locator,String value){
        DriverWait.waitVisibilityOfElementLocated(locator,5);
        Select select=new Select(findElement(locator));
        select.selectByValue(value);
        return select;
    }

    public Select selectByVisibleText(By locator,String visibleText){
        DriverWait.waitVisibilityOfElementLocated(locator,5);
        Select select=new Select(findElement(locator));
        select.selectByVisibleText(visibleText);
        return select;
    }

    public Select selectByIndex(By locator,Integer[] indexes){
        DriverWait.waitVisibilityOfElementLocated(locator,5);
        Select selectObject =new Select(findElement(locator));
        Arrays.asList(indexes).forEach(selectObject::selectByIndex);
        return selectObject;
    }

    public Select selectByValue(By locator,String[] values){
        DriverWait.waitVisibilityOfElementLocated(locator,5);
        Select selectObject =new Select(findElement(locator));
        Arrays.asList(values).forEach(selectObject::selectByVisibleText);
        return selectObject;
    }

    public Select selectByVisibleText(By locator,String[] visibleTexts){
        DriverWait.waitVisibilityOfElementLocated(locator,5);
        Select selectObject=new Select(findElement(locator));
        Arrays.asList(visibleTexts).forEach(selectObject::selectByVisibleText);
        return selectObject;
    }

    public void safeClick(By locator) {
        retryOperation(locator, element -> {
            element.click();
            return null;
        });
    }

    public static void safeSendKeys(By locator, String text) {
        retryOperation(locator, element -> {
            element.sendKeys(text);
            return null;
        });
    }

    public static String safeGetText(By locator) {
        return retryOperation(locator, WebElement::getText);
    }

    public static String safeGetAttribute(By locator, String attribute) {
        return retryOperation(locator, element -> Objects.requireNonNull(element.getDomAttribute(attribute)));
    }

    public <T> T retryOperation(By locator, Function<WebElement, T> operation) {
        int attempts = 0;
        WebDriver driver = DriverInitializer.getWebDriver();
        while (attempts < 5) {
            try {
                WebElement element = driver.findElement(locator);
                return operation.apply(element);
            } catch (StaleElementReferenceException e) {
                attempts++;
                log.warn("Attempt {} failed for element {}. Retrying...", attempts, locator);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        }
        throw new RuntimeException("Failed to interact with element after multiple attempts : " + attempts);
    }


}

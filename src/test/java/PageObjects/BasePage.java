package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Date;
import java.util.List;


import static steps.Driver.getDriver;

public class BasePage {

    WebElement tryGetWebElement(final By locator) {
        try {
            return  getDriver().findElement(locator);
        } catch (Exception e) {
            return null;
        }
    }

    List<WebElement> tryGetWebElements(By locator) {
        try {
            return  getDriver().findElements(locator);
        } catch (Exception e) {
            return null;
        }
    }

    protected WebElement tryGetWebElement(final By locator, final long miliseconds) {
        Date timeStart = new Date();
        WebElement result;
        do {
            result = tryGetWebElement(locator);
        }
        while ((timeStart.getTime() + miliseconds > new Date().getTime()) | result == null);
        return result;
    }

    List<WebElement> tryGetWebElements(final By locator, final long miliseconds) {
        Date timeStart = new Date();
        List<WebElement> result;
        do {
            result = tryGetWebElements(locator);
        }
        while ((timeStart.getTime() + miliseconds > new Date().getTime()) | result.size() == 0);
        return result;
    }
}

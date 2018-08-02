package steps;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class BaseSteps {
    private static Logger log = Logger.getLogger(BaseSteps.class.getName());

    WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    static Map<String, String> tempMap = new HashMap<String, String>();

    void tryClickButton(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            tryScrollToWebElement(element);
            element.click();
        }
        System.out.println("Кнопка была нажата");
    }

    void changeValueInField(WebElement field, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(field));
        field.clear();
        field.sendKeys(value);
        System.out.println("Поле было заполнено значением " + value);
    }

    void trySelectRadioButton(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String oldClass = element.getAttribute("class");
        element.click();
        String newClass = element.getAttribute("class");
        Assert.assertFalse("RadioButton не был выбран", newClass.equals(oldClass));
        System.out.println("RadioButton был отмечен успешно");
    }

    void elementIsDisplayed(WebElement element) {
        try {
            Assert.assertTrue("Элемент не отобразился", element.isDisplayed());
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            Assert.assertTrue("Элемент не отобразился", element.isDisplayed());
        }
        System.out.println("Элемент отобразился");
    }

    void tryScrollToWebElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}

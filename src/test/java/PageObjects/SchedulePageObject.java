package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * PageObject элементов на странице расписание
 */
public class SchedulePageObject extends BasePage {

    private static final String WHENCE_FIELD = "//div[@class='search-form__from']//input[@class='station-input_search__control']";
    private static final String WHERE_FIELD = "//div[@class='search-form__to']//input[@class='station-input_search__control']";
    private static final String WHEN_FIELD = "//div[@class='search-form__when']//input[@class='date-input_search__input']";
    private static final String TRANSPORT_RADIO_BUTTON = "//label[@class='y-radio-group_islet-large__label']//input[@value = '%s']/..";
    private static final String FIND_BUTTON = "//button[@class = 'y-button_islet-rasp-search _pin-left _init']";
    private static final String NEXT_SATURDAY = "//div[@class = 'y-popup_islet _init _animate _position_bottom _shown']//div[@class = 'calendar__day _today']/following-sibling::div[@class = 'calendar__day _weekend']";
    private static final String WEEKEND_TODAY = "//div[@class = 'y-popup_islet _init _animate _position_bottom _shown']//div[@class = 'calendar__day _weekend _today']/following-sibling::div[@class = 'calendar__day _weekend']";
    private static final String WEEKEND_TODAY_SATURDAY = "//div[@class = 'y-popup_islet _init _animate _position_bottom _shown']//div[@class = 'calendar__day _weekend _today']";
    public WebElement getWhenceField() {
        return tryGetWebElement(By.xpath(WHENCE_FIELD));
    }

    public WebElement getWhereField() {
        return tryGetWebElement(By.xpath(WHERE_FIELD));
    }

    public WebElement getWhenField() {
        return tryGetWebElement(By.xpath(WHEN_FIELD));
    }

    public WebElement getTrainRadioButton(String value) {
        return tryGetWebElement(By.xpath(String.format(TRANSPORT_RADIO_BUTTON, value)));
    }

    public WebElement getFindButton() {
        return tryGetWebElement(By.xpath(FIND_BUTTON));
    }

    public WebElement getNextSaturday() {
        return tryGetWebElement(By.xpath(NEXT_SATURDAY));
    }

    public WebElement getWeekendToday() {
        return tryGetWebElement(By.xpath(WEEKEND_TODAY));
    }

    public WebElement getWeekendTodaySaturday() {
        return tryGetWebElement(By.xpath(WEEKEND_TODAY_SATURDAY));
    }


}

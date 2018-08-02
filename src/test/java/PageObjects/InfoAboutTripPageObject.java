package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InfoAboutTripPageObject extends BasePage{
    private static final String INFO_ABOUT_TRIP = "b-width__inner";
    private static final String INFO_ABOUT_TRIP_TITLE = "b-page-title__title";
    private static final String DEPARTURE_TIME = "//span[@class = 'b-timetable__time i-time i-bem']";
    private static final String ARRIVAL_TIME = "//tr[@class = 'b-timetable__row b-timetable__row_position_last b-timetable__row_type_end']//span[@class = 'b-timetable__time i-time i-bem']";
    private static final String TRAVEL_TIME = "//tr[@class = 'b-timetable__row b-timetable__row_position_last b-timetable__row_type_end']//td[@class = 'b-timetable__cell b-timetable__cell_type_time b-timetable__cell_position_last']";
    private static final String CITY_START = "//tr[@class = 'b-timetable__row b-timetable__row_type_start']//div[@class = 'b-timetable__city']";
    private static final String CITY_END = "//tr[@class = 'b-timetable__row b-timetable__row_position_last b-timetable__row_type_end']//a[@class = 'b-link']";

    public WebElement getInfoAboutTrip() {
        return tryGetWebElement(By.className(INFO_ABOUT_TRIP));
    }

    public WebElement getTitleInfoAboutTrip() {
        return tryGetWebElement(By.className(INFO_ABOUT_TRIP_TITLE));
    }

    public WebElement getDepartureTime() {
        return tryGetWebElement(By.xpath(DEPARTURE_TIME));
    }

    public WebElement getArrivalTime() {
        return tryGetWebElement(By.xpath(ARRIVAL_TIME));
    }

    public WebElement getTravelTime() {
        return tryGetWebElement(By.xpath(TRAVEL_TIME));
    }

    public WebElement getCityStart() {
        return tryGetWebElement(By.xpath(CITY_START));
    }

    public WebElement getCityEnd() {
        return tryGetWebElement(By.xpath(CITY_END));
    }
}

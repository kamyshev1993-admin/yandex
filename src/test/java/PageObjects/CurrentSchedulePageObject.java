package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CurrentSchedulePageObject extends BasePage {

    private static final String SEARCH_PAGE = "SearchPage__content";
    private static final String SEARCH_TITLE = "SearchTitle";
    private static final String TRIP_LIST = "//section[@class = 'SearchSegments']/article[@class = 'SearchSegment SearchSegment_isNotInterval SearchSegment_isNotGone SearchSegment_isVisible']";

    public WebElement getSearchPage() {
        return tryGetWebElement(By.className(SEARCH_PAGE));
    }

    public WebElement getSearchTitle() {
        return tryGetWebElement(By.className(SEARCH_TITLE));
    }

    public List<WebElement> getTripList() {
        return tryGetWebElements(By.xpath(TRIP_LIST));
    }



}

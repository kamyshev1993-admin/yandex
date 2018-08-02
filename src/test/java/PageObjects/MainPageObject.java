package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageObject extends BasePage{

    private static final String SCHEDULE = "Расписания";

    public WebElement getSchedule() {
        return tryGetWebElement(By.linkText(SCHEDULE));
    }
}

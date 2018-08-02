package steps;

import PageObjects.SchedulePageObject;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static junit.framework.TestCase.fail;

/**
 * В данном классе будут описаны шаги с элементами на странице "Расписание пригородного и междугородного транспорта"
 */

public class ScheduleSteps extends BaseSteps {
    private static String transport;
    private SchedulePageObject page = new SchedulePageObject();

    static String getTransport() {
        return transport;
    }

    @Пусть("^Расписание пригородного и междугородного транспорта > поле \"откуда\" изменено на значение \"([^\"]*)\"$")
    public void changeValueInFieldWhence(String value) {
        changeValueInField(page.getWhenceField(), value);
        tempMap.put("whence", value);
    }

    @Пусть("^Расписание пригородного и междугородного транспорта > поле \"куда\" изменено на значение \"([^\"]*)\"$")
    public void changeValueInFieldWhere(String value) {
        changeValueInField(page.getWhereField(), value);
        tempMap.put("where", value);
    }

    @И("^Расписание пригородного и междугородного транспорта > в поле \"когда\" выбрано значение \"Ближайшая суббота\"$")
    public void chooseDateNextSaturday() {
        page.getWhenField().click();
        try {
            wait.until(ExpectedConditions.visibilityOf(page.getNextSaturday()));
            page.getNextSaturday().click();
        } catch (NullPointerException ex) {
            Calendar calendar = Calendar.getInstance();
            if (new SimpleDateFormat("EEEE").format(calendar.getTime()).equals("суббота")) {
                wait.until(ExpectedConditions.visibilityOf(page.getWeekendTodaySaturday()));
                page.getWeekendTodaySaturday().click();
            } else {
                wait.until(ExpectedConditions.visibilityOf(page.getWeekendToday()));
                page.getWeekendToday().click();
            }
        }
        tempMap.put("date", page.getWhenField().getAttribute("value"));
    }

    @И("^Расписание пригородного и междугородного транспорта > выбран радио баттон \"([^\"]*)\"$")
    public void selectSuburbanRadioButton(String text) {
        String newValue;
        String value;
        switch (text) {
            case "Электричка":
                newValue = "suburban";
                value = "электричек";
                break;
            case "Самолет":
                newValue = "plane";
                value = "самолётов";
                break;
            case "Поезд":
                newValue = "train";
                value = "поездов";
                break;
            case "Автобус":
                newValue = "bus";
                value = "автобусов";
                break;
            default:
                fail("Поле передано не корректно");
                newValue = null;
                value = null;
        }
        trySelectRadioButton(page.getTrainRadioButton(newValue));
        tempMap.put(newValue, value);
        transport = newValue;
    }

    @И("^Расписание пригородного и междугородного транспорта > нажата нкопка \"Найти\"$")
    public void tryClickFindButton() {
        tryClickButton(page.getFindButton());
    }
}

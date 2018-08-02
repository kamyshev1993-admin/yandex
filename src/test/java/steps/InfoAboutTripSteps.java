package steps;

import PageObjects.InfoAboutTripPageObject;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;

import static junit.framework.Assert.fail;

public class InfoAboutTripSteps extends BaseSteps {
    private InfoAboutTripPageObject page = new InfoAboutTripPageObject();

    @То("^открыта страница Информация о рейсе$")
    public void titleInfoAboutTripIsDisplayed() {
        elementIsDisplayed(page.getInfoAboutTrip());
    }

    @И("^Информация о рейсе > заголовок соответствует выбранным параметрам$")
    public void checkTitleInfoAboutTrip() {
        String title;
        try {
            title = page.getTitleInfoAboutTrip().getText();
        } catch (StaleElementReferenceException ex) {
            title = page.getTitleInfoAboutTrip().getText();
        }
        title = title.split(",")[1].trim();
        Boolean result = title.equals(tempMap.get("trip"));
        Assert.assertTrue("Титульник некорректный", result);
        System.out.println("Титульник корректный, = " + title);

    }

    @И("^Информация о рейсе > время \"([^\"]*)\" заполнено валидным значеним$")
    public void checkTimesTrip(String value) {
        String text;
        switch (value) {
            case "отправления":
                text = tempMap.get("timeTrip") + ", " + tempMap.get("date");
                Assert.assertEquals("Время и дата отправления не соответствует ожиданиям", text, page.getDepartureTime().getText());
                System.out.println("Время и дата отправления соответствует ожиданиям " + text);
                break;
            case "прибытия":
                text = tempMap.get("arrivalTime");
                Assert.assertEquals("Время прибытия не соответствует ожиданиям", text, page.getArrivalTime().getText());
                System.out.println("Время прибытия соответствует ожиданиям " + text);
                break;
            case "в пути":
                text = tempMap.get("durationTime");
                Assert.assertEquals("Время в пути не соответствует ожиданиям", text, page.getTravelTime().getText());
                System.out.println("Время в пути соответствует ожиданиям " + text);
                break;
            default:
                fail("Поле передано не корректно");

        }
    }

    @И("^Информация о рейсе > поле город \"([^\"]*)\" заполнено валидным значением$")
    public void checkCitiesTrip(String value) {
        String text;
        switch (value) {
            case "отправления":
                text = tempMap.get("whence");
                Assert.assertTrue("Город отправления не соответствует ожиданиям", page.getCityStart().getText().contains(text));
                System.out.println("Город отправления соответствует ожиданиям");
                break;
            case "прибытия":
                text = tempMap.get("where");
                Assert.assertTrue("Город прибытия не соответствует ожиданиям", page.getCityEnd().getText().contains(text));
                System.out.println("Город прибытия соответствует ожиданиям");
                break;
            default:
                fail("Поле передано не корректно");
        }
    }
}

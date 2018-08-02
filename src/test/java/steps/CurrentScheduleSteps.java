package steps;

import PageObjects.CurrentSchedulePageObject;
import cucumber.api.java.ru.И;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;


public class CurrentScheduleSteps extends BaseSteps {
    private CurrentSchedulePageObject page = new CurrentSchedulePageObject();
    private String rubPrice;
    private String timeTrip;

    private void checkInfo(String text) {
        Assert.assertTrue("Заголовок не содержит пункта отправления", text.contains(tempMap.get("whence")));
        System.out.println("Пункт отправления корректный");
        Assert.assertTrue("Заголовок не содержит пункта назначения", text.contains(tempMap.get("where")));
        System.out.println("Пункт назанчения корректный");
        Assert.assertTrue("Заголовок не содержит пункта назначения", text.contains(tempMap.get("date")));
        System.out.println("Дата отправления корректная");
        Assert.assertTrue("Заголовок не содержит пункта назначения", text.contains(tempMap.get(ScheduleSteps.getTransport())));
        System.out.println("Транспорт корректный");

    }

    @И("^отображена страница со списком Расписаний с выбранными параметрами$")
    public void searchPageIsDisplayed() {
        elementIsDisplayed(page.getSearchPage());
    }

    @И("^Расписаний с выбранными параметрами > Произведена проверка загаловка$")
    public void checkSearchTitle() {
       try {
           checkInfo(page.getSearchTitle().getText());
       } catch (StaleElementReferenceException ex) {
           checkInfo(page.getSearchTitle().getText());
       }
    }

    @И("^Расписаний с выбранными параметрами > отображена таблица с возможными рейсами")
    public void checkTripList() {
        List<WebElement> listOfTrip = page.getTripList();
        Assert.assertTrue("Таблица пустая", listOfTrip.size() != 0);
        System.out.println("Таблицы с возможными рейсам отображена");
    }

    @И("^Таблица с возможными рейсами > выбран самый ранний рейс, который отправляется не ранее \"([^\"]*)\" и билет стоит не более \"([^\"]*)\"$")
    public void getTrip(String time, String sum) {
        List<WebElement> list;
        try {
            list = filterTrip(time, sum);
        } catch (StaleElementReferenceException ex) {
            list = filterTrip(time, sum);
        }
        Assert.assertTrue("Рейсов с данными параметрами не найдено", list.size() != 0);
        WebElement element = list.get(0);
        rubPrice = element.findElement(By.xpath(".//span[@class = 'Price SuburbanTariffs__buttonPrice']")).getText().replaceAll("\\D", "");
        timeTrip = element.findElement(By.className("SearchSegment__time")).getText();
        String arrivalTime = element.findElement(By.xpath(".//div[@class = 'SearchSegment__dateTime']/span[@class = 'SearchSegment__time']")).getText();
        String durationTime = element.findElement(By.xpath(".//div[@class = 'SearchSegment__times']//div[@class = 'SearchSegment__duration']")).getText();
        WebElement trip = element.findElement(By.xpath(".//span[@class = 'SegmentTitle__title']"));
        tempMap.put("trip", trip.getText());
        tempMap.put("timeTrip", timeTrip);
        tempMap.put("arrivalTime", arrivalTime);
        tempMap.put("durationTime", durationTime);
        tryClickButton(trip);
    }

    private List<WebElement> filterTrip(String time, String sum) {
        return page.getTripList().stream()
                .filter((a) -> a.findElement(By.className("SearchSegment__time")).getText().compareTo(time) >= 0)
                .filter((a) -> Integer.parseInt(a.findElement(By.xpath(".//span[@class = 'Price SuburbanTariffs__buttonPrice']")).getText().replaceAll("\\D", ""))
                        <= Integer.parseInt(sum))
                .collect(Collectors.toList());
    }

    @И("^Выведена информация о рейсе$")
    public void printInfoAboutTrip() {
        String usdPrice = String.valueOf(String.format("%.2f", (Double.parseDouble(rubPrice) / 62.80)));
        System.out.println("Время отправления = " + timeTrip);
        System.out.println("Стоимость поездки в рублях = " + rubPrice);
        System.out.println("Стоимость поездки в доллорах = " + usdPrice);
    }
}

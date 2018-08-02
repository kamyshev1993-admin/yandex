package steps;

import PageObjects.MainPageObject;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Пусть;

/**
 * В данном классе будут описаны шаги с элементами на главной странице
 */
public class MainPageSteps extends BaseSteps{

    private MainPageObject page = new MainPageObject();

    @Пусть("^Открыта стартовая страница \"([^\"]*)\"$")
    public void openMainPage(String url){
        driver.get(url);

    }

    @Когда("^Основная страница > нажата линк кнопка \"Расписания\"$")
    public void clickLinkButtonSchedule() {
        tryClickButton(page.getSchedule());
    }

    @И("^отладка$")
    public void отладка() {
        int i = 0;
    }
}

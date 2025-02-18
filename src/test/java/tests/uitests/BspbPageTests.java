package tests.uitests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.BspbMainPages;
import ui.BspbSteps;

import static ui.BspbMainPages.unionCard;
import static ui.BspbMainPages.yarkoDeals;

public class BspbPageTests extends BspbBaseTests {

    @Feature("Сайт БСПБ")
    @DisplayName("Тест на проверку отображения элементов на странице дебетовых карт ")
    @Test
    @Step("Отображение на странице элементов")
    public void checkBspbDebitCardsPageTest() {
        Allure.step("Открыть страницу Дебетовых карт на сайте", () -> {
            BspbSteps.openPage();
        });
        Allure.step("проверяем, что ожидаемое значение элемента соответствует фактическому", () -> {
            BspbSteps.checkElementValue(unionCard, "Дебетовая карта Единая карта петербуржца");
        });
    }

    @Test
    public void checkYarkoPartners() {
        Selenide.open(Configuration.baseUrl);
        BspbMainPages.cards.hover();
        BspbMainPages.partnersAndDeals.click();
        Selenide.sleep(3000);
        Assertions.assertTrue(yarkoDeals.stream().allMatch(x -> x.isDisplayed()));
        BspbSteps.checkCountElements(BspbMainPages.yarkoDeals, 27);

    }
}

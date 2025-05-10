package tests.uiTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.BspbSteps;
import ui.pages.BspbMainPages;
import ui.pages.BspbYarkoPages;

import static ui.pages.BspbMainPages.unionCard;

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

    @Feature("Сайт БСПБ")
    @DisplayName("Тест на проверку отображения элементов на странице партнеров Ярко")
    @Test
    public void checkYarkoPartners() {
        Allure.step("Открыть страницу Партнеров программы Ярко на сайте", () -> {
            Selenide.open(Configuration.baseUrl);
            BspbMainPages.cards.hover();
            BspbYarkoPages.partnersAndDeals.click();
        });
        Allure.step("Проверить, что количество элементов должно быть больше заданного значения", () -> {
            BspbSteps.checkCountElements(BspbYarkoPages.yarkoDeals, 1);
        });
        //Assertions.assertTrue(BspbYarkoPages.yarkoDeals.stream().allMatch(x -> x.isDisplayed()));

    }
}

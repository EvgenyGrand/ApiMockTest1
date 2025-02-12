package tests.uitests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ui.BspbMainPages;
import ui.BspbSteps;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$x;
import static ui.BspbMainPages.debitCards;
import static ui.BspbMainPages.unionCard;

public class BspbPageTests extends BspbBaseTests {

    @Feature("Сайт БСПБ")
    @DisplayName("Тест на проверку отображения элементов на странице дебетовых карт ")
    @Test
    @Order(5)
    @Step("Отображение на странице элементов")
    public void checkBspbDebitCardsPageTest() {
        Allure.step("Открыть страницу Дебетовых карт на сайте");
        BspbSteps.openPage();
        BspbSteps.checkElementValue(unionCard, "Дебетовая карта Единая карта петербуржца");

    }
}

package ui;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ui.pages.BspbMainPages;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.codeborne.selenide.Screenshots;

public class BspbSteps {

    @Step("Переходим на страницу Дебетовых карт")
    public static void openPage() {
        Selenide.open(Configuration.baseUrl);
        BspbMainPages.debitCards.click();
    }

    @Step("Проверяем, что значение элемента соответствует значению {expectedValue}")
    public static void checkElementValue(SelenideElement element, String expectedValue) {
        String validationMessage = "Значение элемента совпадает с ожидаемым значением: " + expectedValue;
        String actualValue = element.getText();
        if (actualValue.equals(expectedValue)) {
            Allure.addAttachment("Результат проверки на соответствие ожидаемого значения элемента фактическому", "text/plain", validationMessage);
        } else {
            throw new AssertionError("Фактическое значение элемента = " + actualValue + ". Значение не совпадает с ожидаемым результатом: " + expectedValue);
        }
    }

    @Step("Дожидаемся загрузки элементов из списка")
    public static void elementsIsVisible(List<SelenideElement> elements) {
        for (SelenideElement element : elements) {
            element.shouldBe(Condition.visible);
            //Assertions.assertTrue(BspbYarkoPages.yarkoDeals.stream().allMatch(x -> x.isDisplayed()));
        }
    }

    @Step("проверяем, что количество элементов должно быть больше {count}")
    public static void checkCountElements(List<SelenideElement> elements, int count) {
        Selenide.sleep(3000);
        String validationMessage = "Количество элементов больше, чем " + count;
        if (elements.size() > count) {
            Allure.addAttachment("Результат проверки на соответствие ожидаемого значения элемента фактическому", "text/plain", validationMessage);
            Assertions.assertTrue(true, validationMessage);
        } else {
            String errorMessage = "Фактическое количество элементов = " + elements.size() + ". Ожидалось больше, чем " + count;
            Allure.addAttachment("Ошибка проверки", "text/plain", errorMessage);
            throw new AssertionError(errorMessage);
        }
    }
}

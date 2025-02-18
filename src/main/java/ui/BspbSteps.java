package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.List;

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

    public static void checkCountElements(List<SelenideElement> elements, int count) {
        Assertions.assertEquals(count, elements.size(), "Количество элементов не совпадает");
    }
}


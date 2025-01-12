package apihelpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommonResponce {

    @Step("Проверяем, что значение статуса ответа соответствует значению {expectedStatusCode}")
    public static void checkStatusOk(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();

        // Логируем результаты проверки
        String validationMessage = "Ожидаемый статус-код: " + expectedStatusCode + ", фактический статус-код: " + actualStatusCode;
        Allure.addAttachment("Результаты проверки", "text/plain", validationMessage);

        // Проверка статуса
        if (actualStatusCode != expectedStatusCode) {
            throw new AssertionError("Статус-код не совпадает: " + validationMessage);
        }
    }

    @Step("Проверяем, что значение поля {fieldName} соответствует значению {expectedValue}")
    public static void checkFieldValue(Response response, String fieldName, Object expectedValue) {
        Object actualValue = response.jsonPath().get(fieldName);
        String validationMessage;

        // Проверка значения поля
        if (actualValue.equals(expectedValue)) {
            validationMessage = "Значение поля " + fieldName + " совпадает с ожидаемым значением: " + expectedValue;
        } else {
            validationMessage = "Значение поля " + fieldName + " не совпадает. Ожидалось: " + expectedValue + ", но получено: " + actualValue;
        }
        // Добавление вложения в Allure
        Allure.addAttachment("Результаты проверки", "text/plain", validationMessage);
    }
}
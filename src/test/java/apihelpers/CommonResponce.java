package apihelpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommonResponce {

    @Step("Проверяем, что значение статуса ответа соответствует значению {expectedStatusCode}")
    public static void checkStatusOk(Response response, int expectedStatusCode) {
        ApiHelpers.attacheResponseBodyToAllureReport(response);
        int actualStatusCode = response.getStatusCode();
        String validationMessage = "Ожидаемый статус-код: " + expectedStatusCode + ", фактический статус-код: " + actualStatusCode;
        Allure.addAttachment("Результаты проверки на соответствие ожидаемого статуса ответа фактическому", "text/plain", validationMessage);
        if (actualStatusCode != expectedStatusCode) {
            throw new AssertionError("Статус-код не совпадает: " + validationMessage);
        }
    }

    @Step("Проверяем, что значение поля {fieldName} соответствует значению {expectedValue}")
    public static void checkFieldValue(Response response, String fieldName, Object expectedValue) {
        String validationMessage = "Значение поля " + fieldName + " совпадает с ожидаемым значением: " + expectedValue;
        Object actualValue = response.jsonPath().get(fieldName);
        if (actualValue.equals(expectedValue)) {
            Allure.addAttachment("Результаты проверки на соответствие ожидаемого значения поля фактическому", "text/plain", validationMessage);
        } else {
            throw new AssertionError("Значение поля " + fieldName + " не совпадает с ожидаемым значением: " + expectedValue);
        }
    }
}
package apihelpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommonResponce {

     @Step("Проверяем, что статус ответа соответствует статусу {code}")
    public static void checkStatusOk(Response response, int code) {
        response.then().statusCode(code);
    }

    @Step("Проверяем, что значение поля {fieldName} соответствует значению {expectedValue}")
    public static void checkFieldValue(Response response, String fieldName, Object expectedValue) {
        Object actualValue = response.jsonPath().get(fieldName);
        assertThat("Значение поля " + fieldName + " не совпадает.", actualValue, equalTo(expectedValue));
    }

}

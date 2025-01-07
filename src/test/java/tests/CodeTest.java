package tests;

import apihelpers.CommonResponce;
import apihelpers.EndPoints;
import apihelpers.ApiHelpers;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CodeTest extends BaseTest {

    Response response;
    @Feature("Платежный qr")
    @DisplayName("Тест на проверку регистрации qr ")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @Step("Создание qr")
    public void registrationCodeTest() {
       response = ApiHelpers.postRequestWithBody(EndPoints.postRegistrationCode);
      CommonResponce.checkStatusOk(response, 200);
      CommonResponce.checkFieldValue(response, "data.code", "QWERTY123");

    }
    @Feature("Платежный qr")
    @DisplayName("Тест на проверку активации qr ")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @Step("Создание qr")
    public void activationCodeTest() {
        Map<String, Object> activationCode = new HashMap<>();
        activationCode.put("code", "QWERTY123");
        ApiHelpers.installSpecification(ApiHelpers.requestSpec(EndPoints.baseUrl), ApiHelpers.responceSpec());
        given()
                .body(activationCode)
                .when()
                .post(EndPoints.postActivationationCode)
                .then().log().all()
                .body("data.parameter", equalTo("UUID123"));
    }
}
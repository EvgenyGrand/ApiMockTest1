package tests;

import apihelpers.ApiHelpers;
import apihelpers.CommonResponce;
import apihelpers.EndPoints;
import apihelpers.RequestsBody;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


public class CodeTest extends BaseTest {

    Response response;

    @Feature("Платежный qr")
    @DisplayName("Тест на проверку регистрации qr ")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @Step("Создание qr")
    public void registrationCodeTest() {
        Allure.step("Регистрация qr", () -> {
            response = ApiHelpers.postRequestWithBody(EndPoints.postRegistrationCode, RequestsBody.registrationQr());
            CommonResponce.checkStatusOk(response, 200);
            CommonResponce.checkFieldValue(response, "data.code", "QWERTY123");
            code = response.jsonPath().getString("data.code");
        });
    }

    @Feature("Платежный qr")
    @DisplayName("Тест на проверку активации qr ")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @Step("Создание qr")
    public void activationCodeTest() {
        Allure.step("Регистрация qr", () -> {
            response = ApiHelpers.postRequestWithBody(EndPoints.postRegistrationCode, RequestsBody.registrationQr());
            CommonResponce.checkStatusOk(response, 200);
            CommonResponce.checkFieldValue(response, "data.code", "QWERTY123");
            code = response.jsonPath().getString("data.code");
        });

        Allure.step("Активация qr", () -> {
            response = ApiHelpers.postRequestWithBody(EndPoints.postActivationationCode, RequestsBody.activationQr(BaseTest.code));
            CommonResponce.checkStatusOk(response, 200);
            CommonResponce.checkFieldValue(response, "data.parameter", "UUID123");
        });

        Allure.step("Получение информации о qr", () -> {
            response = ApiHelpers.getRequestWithQueryParams(EndPoints.getCodeInfo, RequestsBody.getQrInfo(BaseTest.code));
            CommonResponce.checkStatusOk(response, 200);
            CommonResponce.checkFieldValue(response, "data.info", "UUID123");
        });
    }
}
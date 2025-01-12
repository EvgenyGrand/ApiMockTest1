package tests;

import apihelpers.ApiHelpers;
import apihelpers.CommonResponce;
import apihelpers.EndPoints;
import apihelpers.RequestsBody;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
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
        response = ApiHelpers.postRequestWithBody(EndPoints.postRegistrationCode, RequestsBody.registrationQr());
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
        response = ApiHelpers.postRequestWithBody(EndPoints.postActivationationCode, RequestsBody.activationQr());
        CommonResponce.checkStatusOk(response, 200);
        CommonResponce.checkFieldValue(response, "data.parameter", "UUID123");
    }
}
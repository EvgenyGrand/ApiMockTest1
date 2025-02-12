package tests.apiests;

import apihelpers.ApiHelpers;
import apihelpers.CommonResponce;
import apihelpers.EndPoints;
import apihelpers.RequestsBody;
import dataBase.SqlExecutor;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class CodeTest extends BaseTest {

    Response response;

    @Feature("Платежный qr")
    @DisplayName("Тест на проверку регистрации qr ")
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

    // Не забыть! В JUnit 5, если тест параметризован надо убрать аннотацию @Test, оставить только @ParameterizedTest
    @Feature("Платежный qr")
    @DisplayName("Тест на проверку активации qr ")
    @Order(2)
    @Step("Активация qr")
    @ParameterizedTest(name = "Тест на проверку активации qr => queryParam={0}")
    // подумать как передать параметр DisplayName
    @CsvSource({
            "QWERTY123",
            "QWERTY126"
    })

    public void activationCodeTest(String queryParam) {
        Allure.step("Регистрация qr", () -> {
            response = ApiHelpers.postRequestWithBody(EndPoints.postRegistrationCode, RequestsBody.registrationQr());
            CommonResponce.checkStatusOk(response, 200);
            CommonResponce.checkFieldValue(response, "data.code", "QWERTY123");
            code = response.jsonPath().getString("data.code");
        });

        Allure.step("Активация qr", () -> {
            response = ApiHelpers.postRequestWithBody(EndPoints.postActivationationCode, RequestsBody.activationQr(BaseTest.code));
            CommonResponce.checkStatusOk(response, 200);
            CommonResponce.checkFieldValue(response, "data.parameter", BaseTest.expectedCode);
        });

        Allure.step("Получение информации о qr", () -> {
            response = ApiHelpers.getRequestWithQueryParams(EndPoints.getCodeInfo, RequestsBody.getQrInfo(queryParam));
            CommonResponce.checkStatusOk(response, 200);
            CommonResponce.checkFieldValue(response, "data.status", BaseTest.getStatus);
        });
    }

    @Feature("Платежный qr")
    @DisplayName("Тест на проверку наличия qr в БД ")
    @Test
    @Order(3)
    @Step("Проверяем наличие qr в БД")
    public void checkQrInDb() {
        Allure.step("Создаем запись в БД", () -> {
            SqlExecutor.createTable();
            SqlExecutor.addQRCode(BaseTest.expectedCode);
        });
        Allure.step("Проверяем соответствия фактической записи в БД ожидаемой", () -> {
            SqlExecutor.checkVerifyQrCodeInDb(1, BaseTest.expectedCode);
        });
    }
}
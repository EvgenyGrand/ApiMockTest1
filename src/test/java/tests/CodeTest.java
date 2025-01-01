package tests;

import apihelpers.EndPoints;
import apihelpers.Specification;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CodeTest extends BaseTest {

    @Test
    @Order(1)
    public void registrationCodeTest() {
        Specification.installSpecification(Specification.requestSpec(EndPoints.baseUrl), Specification.responceSpecOk200());
        given()
                .body("{}")
                .when()
                .post(EndPoints.postRegistrationCode)
                .then().log().all()
                .body("message", equalTo("Запрос обработан успешно"))
                .body("data.code", equalTo("QWERTY123"));
    }

    @Test
    @Order(2)
    public void activationCodeTest() {
        Map<String, String> activationCode = new HashMap<>();
        activationCode.put("code", "QWERTY123");
        Specification.installSpecification(Specification.requestSpec(EndPoints.baseUrl), Specification.responceSpecOk200());
        given()
                .body(activationCode)
                .when()
                .post(EndPoints.postActivationationCode)
                .then().log().all()
                .body("data.parameter", equalTo("UUID123"));
    }
}
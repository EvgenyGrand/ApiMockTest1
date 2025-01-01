package tests;

import apihelpers.EndPoints;
import apihelpers.Specification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class CodeTest extends BaseTest {

    @Test
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


}
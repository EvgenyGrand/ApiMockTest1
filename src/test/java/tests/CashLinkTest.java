package tests;

import apihelpers.EndPoints;
import apihelpers.Specification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CashLinkTest extends BaseTest {

    @Test
    public void RegistrationCashLinkTest() {
        Specification.installSpecification(Specification.requestSpec(EndPoints.baseUrl), Specification.responceSpecOk200());
        given()
                .body("{}")
                .when()
                .post(EndPoints.postRegistrationCashLink)
                .then().log().all()
                .body("data.qrcId", equalTo("BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8"))
                .body("data.payload", equalTo("https://qr.nspk.ru/BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8"))
                .body("data.status", equalTo("CREATED"));
        BaseTest.qrcId = jsonPath.getString("data.qrcId");


    }
}
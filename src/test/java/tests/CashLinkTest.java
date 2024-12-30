package tests;

import apihelpers.EndPoints;
import apihelpers.Specification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import service.RegistrationCashLinkMock;
import service.WireMockServerSetup;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CashLinkTest extends BaseTest {

    @Test
    public void registrationCashLinkTest() throws JsonProcessingException {
        Specification.installSpecification(Specification.requestSpec(EndPoints.baseUrl), Specification.responceSpecOk200());
        given()
                .body("{}")
                .when()
                .post(EndPoints.postRegistrationCashLink)
                .then().log().all()
                .body("data.qrcId", equalTo(RegistrationCashLinkMock.qrcId))
                .body("data.payload", equalTo("https://qr.nspk.ru/BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8"))
                .body("data.status", equalTo("CREATED"));
    }
}
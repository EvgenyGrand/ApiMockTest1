import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiMockTest extends BaseTest {

    @Test
    public void testApiRequest() {
        // Выполняем запрос к замокированному API с использованием Rest Assured
        given()
                .header("Merchant-Authorization", "IPSkorobogatov")
                .header("Content-Type", "application/json")
                .body("{}")
                .when()
                .post("http://localhost:8080/api/v1/qr/cash")
                .then().log().all()
                .statusCode(200)
                .contentType("application/json")
                .body("data.qrcId", equalTo("BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8"))
                .body("data.payload", equalTo("https://qr.nspk.ru/BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8"))
                .body("data.status", equalTo("CREATED"));
    }
}
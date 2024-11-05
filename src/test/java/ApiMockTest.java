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

public class ApiMockTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        // Запускаем WireMock сервер на порту 8080
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
        wireMockServer.start();

        // Настраиваем мок ответа
        WireMock.configureFor("localhost", 8080);
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseData.Data responseData = new ResponseData.Data(
                "BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8",
                "https://qr.nspk.ru/BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8",
                "CREATED"
        );

        ResponseData response = new ResponseData("RQ00000", "Запрос обработан успешно", responseData);

        stubFor(post(urlEqualTo("/api/v1/qr/cash"))
                .withHeader("Merchant-Authorization", WireMock.equalTo("IPSkorobogatov"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalTo("{}"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(response))));
    }

    @AfterEach
    public void tearDown() {
        // Останавливаем сервер после теста
        wireMockServer.stop();
    }

    @Test
    public void testApiRequest() {
        // Выполняем запрос к замокированному API с использованием Rest Assured
        given()
                .header("Merchant-Authorization", "IPSkorobogatov")
                .header("Content-Type", "application/json")
                .body("{}")
                .when()
                .post("http://localhost:8080/api/v1/qr/cash")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("code", equalTo("RQ00000"))
                .body("message", equalTo("Запрос обработан успешно"))
                .body("data.qrcId", equalTo("BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8"))
                .body("data.payload", equalTo("https://qr.nspk.ru/BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8"))
                .body("data.status", equalTo("CREATED"));
    }
}
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WireMockSetup {

    private WireMockServer wireMockServer;

    public void startServer() throws JsonProcessingException {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
        wireMockServer.start();

        WireMock.configureFor("localhost", 8080);
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseData.Data responseData = new ResponseData.Data(
                "BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8",
                "https://qr.nspk.ru/BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8",
                "CREATED"
        );

        ResponseData response = new ResponseData("RQ00000", "Запрос обработан успешно", responseData);

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/v1/qr/cash"))
                .withHeader("Merchant-Authorization", WireMock.equalTo("IPSkorobogatov"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalTo("{}"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(response))));
    }

    public void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
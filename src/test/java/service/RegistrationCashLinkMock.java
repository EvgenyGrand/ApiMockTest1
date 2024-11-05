package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import dto.RegistrationCashLinkDto;

public class RegistrationCashLinkMock {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void setupStub() throws JsonProcessingException {
        RegistrationCashLinkDto.Data responseData = new RegistrationCashLinkDto.Data(
                "BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8",
                "https://qr.nspk.ru/BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8",
                "CREATED"
        );

        RegistrationCashLinkDto response = new RegistrationCashLinkDto("RQ00000", "Запрос обработан успешно", responseData);

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/v1/qr/cash"))
                .withHeader("Merchant-Authorization", WireMock.equalTo("IPSkorobogatov"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalTo("{}"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(response))));
    }
}
package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import dto.RegistrationCashLinkDto;

public class RegistrationCashLinkMock {

    public ObjectMapper objectMapper;
    public static final String qrcId = "BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8";
    public static final String payload = "https://qr.nspk.ru/BS1R005K1SNNNVMA8OLOTTFGU6V5EOC8";
    public static final String status = "CREATED";

    public  RegistrationCashLinkMock() {
        this.objectMapper = new ObjectMapper();
    }

    public void setupStub() throws JsonProcessingException {
        RegistrationCashLinkDto.DataRegistration responseDataRegistration = new RegistrationCashLinkDto.DataRegistration(
                qrcId,
                payload,
                status
        );

        RegistrationCashLinkDto response = new RegistrationCashLinkDto(
                "RQ00000",
                "Запрос обработан успешно",
                responseDataRegistration);

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/v1/qr/cash"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalTo("{}"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(response))));
    }
}
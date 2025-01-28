package service;

import com.github.tomakehurst.wiremock.client.WireMock;

import static service.MockResponse.*;

public class StubsService {


    public void setupStubRegistration() {

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/code/registration"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalToJson("{'amount': '100'}"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(registrationCodeResponse)));
    }

    public void setupStubActivation() {
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/code/activation"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalToJson("{'code': 'QWERTY123'}")) // Ожидаем правильный JSON
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(activationCodeResponse)));
    }

    public void setupStubGetInfo() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/code/info?code=QWERTY123"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(getCodeInfoResponse)));
    }
}
package service;

import com.github.tomakehurst.wiremock.client.WireMock;

import static service.MockResponse.activationCodeResponse;
import static service.MockResponse.registrationCodeResponse;

public class RegistrationCodeMock {



    public void setupStubRegistration() {

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/code/registration"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalTo("{}"))
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
}
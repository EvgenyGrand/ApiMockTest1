package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WireMockServerSetup {

    private WireMockServer wireMockServer;

    public void startServer() throws JsonProcessingException {
        // Создаем экземпляр WireMockServer
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
        wireMockServer.start();

        // Конфигурируем WireMock для работы с локальным сервером
        WireMock.configureFor("localhost", 8080);

        // Настраиваем заглушки
        RegistrationCashLinkMock registrationCashLinkMock = new RegistrationCashLinkMock();
        registrationCashLinkMock.setupStub();
    }

    public void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
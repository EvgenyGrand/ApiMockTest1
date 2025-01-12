package service;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import groovy.lang.Lazy;

public class WireMockServerSetup {

    private WireMockServer wireMockServer;

    public void startServer() {
        // Создаем экземпляр WireMockServer
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
        wireMockServer.start();

        // Конфигурируем WireMock для работы с локальным сервером
        WireMock.configureFor("localhost", 8080);

        // Настраиваем заглушки
        StubsService stubsService = new StubsService();
        stubsService.setupStubRegistration();
        stubsService.setupStubActivation();
    }

    public void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
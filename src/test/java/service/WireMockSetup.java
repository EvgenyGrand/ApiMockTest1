package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WireMockSetup {

    private WireMockServer wireMockServer;

    public void startServer() throws JsonProcessingException {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
        wireMockServer.start();

        WireMock.configureFor("localhost", 8080);

        RegistrationCashLinkMock registrationCashLinkMock = new RegistrationCashLinkMock();
        registrationCashLinkMock.setupStub();
    }

    public void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
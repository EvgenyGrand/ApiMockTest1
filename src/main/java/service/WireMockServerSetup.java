package service;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import groovy.lang.Lazy;

public class WireMockServerSetup {

    private WireMockServer wireMockServer;

    public void startServer() {

        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
        wireMockServer.start();

        WireMock.configureFor("localhost", 8080);

        StubsService stubsService = new StubsService();
        stubsService.setupStubRegistration();
        stubsService.setupStubActivation();
        stubsService.setupStubGetInfo();
    }

    public void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
package service;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import groovy.lang.Lazy;
import utils.PropertyLoader;

public class WireMockServerSetup {

    private WireMockServer wireMockServer;

    Integer portNumber = Integer.valueOf(PropertyLoader.getProperty("portNumber"));

    public void startServer() {

        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(portNumber));
        wireMockServer.start();

        WireMock.configureFor("localhost", portNumber);

        StubsService stubsService = new StubsService();
        stubsService.setupStubRegistration();
        stubsService.setupStubActivation();
        stubsService.setupStubGetInfoFirstValue();
        stubsService.setupStubGetInfoSecondValue();
    }

    public void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
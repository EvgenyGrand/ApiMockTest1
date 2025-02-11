package tests;

import apihelpers.ApiHelpers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import service.WireMockServerSetup;

public abstract class BaseTest extends ApiHelpers {


    public static String code;
    public static String expectedCode = "UUID123";
    public static String getStatus = "SUCCESS";

        public WireMockServerSetup wireMockSetup;

        @BeforeEach
        public void setUp() {
            wireMockSetup = new WireMockServerSetup();
            wireMockSetup.startServer();


        }

        @AfterEach
        public void tearDown() {
            wireMockSetup.stopServer();
        }
    }

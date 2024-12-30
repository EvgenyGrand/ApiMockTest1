package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import service.WireMockServerSetup;

public abstract class BaseTest {

    JsonPath jsonPath;

    public static String qrcId = null;

        public WireMockServerSetup wireMockSetup;

        @BeforeEach
        public void setUp() throws JsonProcessingException {
            wireMockSetup = new WireMockServerSetup();
            wireMockSetup.startServer();


        }

        @AfterEach
        public void tearDown() {
            wireMockSetup.stopServer();
        }
    }

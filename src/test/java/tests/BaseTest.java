package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import service.WireMockSetup;

public abstract class BaseTest {

    JsonPath jsonPath;

    public static String qrcId = null;

        protected WireMockSetup wireMockSetup;

        @BeforeEach
        public void setUp() throws JsonProcessingException {
            wireMockSetup = new WireMockSetup();
            wireMockSetup.startServer();
        }

        @AfterEach
        public void tearDown() {
            wireMockSetup.stopServer();
        }
    }

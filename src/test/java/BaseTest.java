
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

    public abstract class BaseTest {

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

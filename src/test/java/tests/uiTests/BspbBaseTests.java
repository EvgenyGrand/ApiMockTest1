package tests.uiTests;

import org.junit.jupiter.api.BeforeEach;
import ui.SelenideConfiguration;

public class BspbBaseTests {

    @BeforeEach
    public void Setup() {
        SelenideConfiguration.setupSettings();
    }
}

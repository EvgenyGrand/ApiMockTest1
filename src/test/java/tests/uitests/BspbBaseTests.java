package tests.uitests;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeEach;
import ui.SelenideConfiguration;

public class BspbBaseTests {

    @BeforeEach
    public void Setup(){
        SelenideConfiguration.setupSettings();
    }
}

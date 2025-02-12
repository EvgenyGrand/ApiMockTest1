package tests.uitests;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeEach;

public class BspbBaseTests {


    @BeforeEach
    public void SelenideSettings(){
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://bspb.ru";
        Configuration.reportsFolder = "target/allure-results";
        Configuration.screenshots = true;
        Configuration.browserSize = "1920x1080";
        Configuration.reopenBrowserOnFail = true;
//        Configuration.holdBrowserOpen = true;

    }


}

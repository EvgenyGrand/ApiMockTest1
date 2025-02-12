package ui;

import com.codeborne.selenide.Configuration;

public class SelenideConfiguration {

    public static void setupSettings(){
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://bspb.ru";
        Configuration.reportsFolder = "target/allure-results";
        Configuration.screenshots = true;
        Configuration.browserSize = "1920x1080";
        Configuration.reopenBrowserOnFail = true;
    }
}

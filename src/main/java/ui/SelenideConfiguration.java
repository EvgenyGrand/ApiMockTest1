package ui;

import api.utils.PropertyLoader;
import com.codeborne.selenide.Configuration;

public class SelenideConfiguration {

    public static void setupSettings(){
        Configuration.browser = PropertyLoader.getProperty("browser");
        Configuration.baseUrl = PropertyLoader.getProperty("BaseUrlBspb");
        Configuration.reportsFolder = "target/allure-results";
        Configuration.screenshots = true;
        Configuration.browserSize = "1920x1080";
        Configuration.reopenBrowserOnFail = true;
    }
}

package ui.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class BspbYarkoPages extends BspbMainPages {

    public static SelenideElement partnersAndDeals = $x("//*[contains(text(),'Партнеры и акции')]");
    public static List<SelenideElement> yarkoDeals = $$x("//div[@class = 'css-1c6psz6']//p");
}


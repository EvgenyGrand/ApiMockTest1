package ui.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class BspbMainPages {


    public static SelenideElement debitCards = $x("//div[@class= 'css-6lkqo6']//*[contains(text(),'Дебетовые карты')]");
    public static SelenideElement unionCard = $x("//div[@class = \"css-ebyn5d\"]//a[contains(text(), 'карта петербуржца')]");
    public static SelenideElement cards = $x("//p[contains(text(),'Карты')]");
}

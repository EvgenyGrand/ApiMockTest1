package ui;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BspbMainPages {



  public static   SelenideElement debitCards = $x("//h3[@class=\"css-sz8qrd\"]//*[contains(text(),'Дебетовые карты')]");
  public static   SelenideElement unionCard = $x("//div[@class = \"css-ebyn5d\"]//a[contains(text(), 'карта петербуржца')]");
}

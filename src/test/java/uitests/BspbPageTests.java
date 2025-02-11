package uitests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;

public class BspbPageTests extends BspbBaseTests {

    SelenideElement debitCards = $x("//h3[@class=\"css-sz8qrd\"]//*[contains(text(),'Дебетовые карты')]");
    SelenideElement unionCard = $x("//div[@class = \"css-ebyn5d\"]//a[contains(text(), 'карта петербуржца')]");


    @Test
    public void bspbDebitCardsPageTest(){

        Selenide.open(Configuration.baseUrl);
        debitCards.click();
        unionCard.should(Condition.text("Дебетовая карта Единая карта петербуржца"));



    }
}

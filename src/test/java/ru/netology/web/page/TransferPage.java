package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement sumField = $("[class=input__control]");
    private SelenideElement cardField = $("[type=tel]");
    private SelenideElement replenishButton = $("[class=button__text]");


    public TransferPage transfer(String sum, DataHelper.CardsInfo cardsInfo, int id) {
        sumField.setValue(sum);
        if (id != 1) {
            cardField.setValue(cardsInfo.getFirst());
        } else {
            cardField.setValue(cardsInfo.getSecond());
        }
        replenishButton.click();
        return new TransferPage();
    }
}

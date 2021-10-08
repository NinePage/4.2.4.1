package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private SelenideElement replenishFirstCardButton = $("[class=button__content]");
  private SelenideElement replenishSecondCardButton = $$("[class=button__content]").get(1);
  private ElementsCollection cards = $$(".list__item");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";

  private SelenideElement heading = $("[data-test-id=dashboard]");

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public int getFirstCardBalance() {
    val text = cards.first().text();
    return extractBalance(text);
  }

  public int getSecondCardBalance() {
    val text = cards.last().text();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }

  public TransferPage transferOnFirst() {
    replenishFirstCardButton.click();
    return new TransferPage();
  }

  public TransferPage transferOnSecond() {
    replenishSecondCardButton.click();
    return new TransferPage();
  }
}

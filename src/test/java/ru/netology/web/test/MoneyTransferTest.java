package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTransferTest {

  @BeforeEach
  public void setUp() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
  }

  @Test
  void shouldTransferOnFirstCard() {

    var cardsInfo = DataHelper.getCardsInfo();
    var cards = new DashboardPage();
    int firstBalanceBefore = cards.getFirstCardBalance();
    int secondBalanceBefore = cards.getSecondCardBalance();
    int replenishSum = 10000;
    var replenishThisCard = cards.transferOnFirst();
    replenishThisCard.transfer(Integer.toString(replenishSum), cardsInfo, 1);
    assertEquals(firstBalanceBefore + replenishSum, cards.getFirstCardBalance());
    assertEquals(secondBalanceBefore - replenishSum, cards.getSecondCardBalance());
  }

  @Test
  void shouldTransferOnSecondCard() {

    var cardsInfo = DataHelper.getCardsInfo();
    var cards = new DashboardPage();
    int firstBalanceBefore = cards.getFirstCardBalance();
    int secondBalanceBefore = cards.getSecondCardBalance();
    int replenishSum = 10000;
    var replenishCard = cards.transferOnSecond();
    replenishCard.transfer(Integer.toString(replenishSum), cardsInfo, 2);
    assertEquals(firstBalanceBefore - replenishSum, cards.getFirstCardBalance());
    assertEquals(secondBalanceBefore + replenishSum, cards.getSecondCardBalance());
  }
}

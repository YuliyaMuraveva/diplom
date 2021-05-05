package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement heading = $$(".heading").find(exactText("Кредит по данным карты"));
    private SelenideElement cardNumber = $(".input [placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $(".input [placeholder='08']");
    private SelenideElement year = $(".input [placeholder='22']");
    private SelenideElement cardOwner = $$(".input__control").get(3);
    private SelenideElement cvc = $(".input [placeholder='999']");
    private SelenideElement proceedButton = $(".form-field button");
    private SelenideElement approvedNotification = $(".notification_status_ok");
    private SelenideElement declinedNotification = $(".notification_status_error");
    private SelenideElement cardNotification = $$(".input__inner").get(0);
    private SelenideElement monthNotification = $$(".input__inner").get(1);
    private SelenideElement yearNotification = $$(".input__inner").get(2);
    private SelenideElement ownerNotification = $$(".input__inner").get(3);
    private SelenideElement cvcNotification = $$(".input__inner").get(4);

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public void credit(DataHelper.CardInfo info) {
        cardNumber.setValue(info.getCardNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        cardOwner.setValue(info.getOwnerName());
        cvc.setValue(info.getCvc());
        proceedButton.click();
    }

    public void approved() {
        approvedNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void declined() {
        declinedNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void invalidCardNotification() {
        monthNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Неверно указан срок действия карты"));
        yearNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Истёк срок действия карты"));
        ownerNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Неверный формат"));
    }

    public void wrongFormatNotification() {
        cardNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Неверный формат"));
        monthNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Неверный формат"));
        yearNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Неверный формат"));
        ownerNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Неверный формат"));
        cvcNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Неверный формат"));
    }

    public void emptyFieldNotification() {
        proceedButton.click();
        cardNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Поле обязательно для заполнения"));
        monthNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Поле обязательно для заполнения"));
        yearNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Поле обязательно для заполнения"));
        ownerNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Поле обязательно для заполнения"));
        cvcNotification.$(".input__sub").shouldBe(visible).shouldHave(ownText("Поле обязательно для заполнения"));
    }
}

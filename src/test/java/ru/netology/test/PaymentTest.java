package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DbHelper;
import ru.netology.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldPaymentApprovedCard() {
        val cardInfo = DataHelper.getValidCardInfo("approved");
        val paymentPage = new OrderPage().goToPayment();
        paymentPage.payment(cardInfo);
        paymentPage.approved();
        assertEquals("APPROVED", DbHelper.getPaymentStatus());
        assertEquals(4500000, DbHelper.getPaymentAmount());
        assertNull(DbHelper.getCreditId());
    }

    @Test
//    @Disabled
    void shouldPaymentDeclinedCard() {
        val cardInfo = DataHelper.getValidCardInfo("declined");
        val paymentPage = new OrderPage().goToPayment();
        paymentPage.payment(cardInfo);
        paymentPage.declined();
        assertEquals("DECLINED", DbHelper.getPaymentStatus());
        assertNull(DbHelper.getCreditId());
    }

    @Test
//    @Disabled
    void shouldGetNotificationInvalidCard() {
        val cardInfo = DataHelper.getInvalidCardInfo("approved");
        val paymentPage = new OrderPage().goToPayment();
        paymentPage.payment(cardInfo);
        paymentPage.invalidCardNotification();
    }

    @Test
//    @Disabled
    void shouldGetNotificationWrongFormatCard() {
        val cardInfo = DataHelper.getInvalidFormatCardInfo("4444");
        val paymentPage = new OrderPage().goToPayment();
        paymentPage.payment(cardInfo);
        paymentPage.wrongFormatNotification();
    }

    @Test
//    @Disabled
    void shouldGetNotificationEmptyFields() {
        val paymentPage = new OrderPage().goToPayment();
        paymentPage.emptyFieldNotification();
    }
}

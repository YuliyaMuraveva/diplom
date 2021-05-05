package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    private DataHelper() {}

    public static Faker faker = new Faker();

    public static String getCardNumber(String card) {
        if (card.equalsIgnoreCase("approved")) {
            return "4444 4444 4444 4441";
        }
        else if (card.equalsIgnoreCase("declined")) {
            return "4444 4444 4444 4442";
        }
        else return card;
    }

    public static String generateMonth() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM");
        String date = LocalDate.now().plusMonths(3).format(format);
        return date;
    }

    public static String generateYear() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String date = LocalDate.now().plusYears(2).format(format);
        return date;
    }

    public static String generateOwnerName() {
        String ownerName = faker.name().lastName().toUpperCase() + " " + faker.name().firstName().toUpperCase();
        return ownerName;
    }

    public static String generateCvc() {
        return Integer.toString(faker.number().numberBetween(100, 999));
    }

    @Value
    public static class CardInfo{
        private String cardNumber;
        private String month;
        private String year;
        private String ownerName;
        private String cvc;
    }

    public static CardInfo getValidCardInfo(String card) {
        return new CardInfo(getCardNumber(card), generateMonth(), generateYear(), generateOwnerName(), generateCvc());
    }

    public static CardInfo getInvalidCardInfo(String card) {
        return new CardInfo(getCardNumber(card), "13", "20", "Иванов Иван", generateCvc());
    }

    public static CardInfo getInvalidFormatCardInfo(String card) {
        return new CardInfo(getCardNumber(card), "444", "4", "4444 @!", "1");
    }
}

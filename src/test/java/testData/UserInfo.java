package testData;

import com.github.javafaker.Faker;
import utils.RandomUtils;

import java.util.Locale;

public class UserInfo {
    public static Faker faker = new Faker(new Locale("eng"));
    public static String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            currentAddress = faker.address().fullAddress(),
            phone = faker.phoneNumber().subscriberNumber(10),
            day = String.valueOf(faker.number().numberBetween(1, 31)),
            month = String.valueOf(RandomUtils.Month.getRandomMonth()),
            year = String.valueOf(faker.number().numberBetween(1960, 2005)),
            date = day + " " + month + "," + year;

}

package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.RandomUtils;
import testData.UserInfo;

import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testData.UserInfo.*;

public class RegistrationFormWithFakerTests {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTests() {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender("Female")
                .setUserNumber(phone)
                .setBirthDate(day, month, year)
                .setSubject("Chemistry")
                .setSHobbies("Reading")
                .uploadPicture("src/test/resources/img.JPG")
                .setCurrentAddress(currentAddress)
                .setStateCity("Uttar Pradesh", "Agra")
                .clickSubmit();


        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName),
                text(lastName),
                text(email),
                text("Female"),
                text(phone),
                text(day + " " + month +',' + year),
                text("Chemistry"),
                text("Reading"),
                text("img.JPG"),
                text(currentAddress),
                text("Uttar Pradesh Agra"));
    }

    @Test
    void fillFormWithMinimumDataTest() {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender("Female")
                .setUserNumber(phone)
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", "Female")
                .checkResult("Mobile", phone);

    }
}

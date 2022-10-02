package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormWithPageObjectsTests {
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
                .setFirstName("Elina")
                .setLastName("Kim")
                .setEmail("Elina@yandex.ru")
                .setGender("Female")
                .setUserNumber("8987822639")
                .setBirthDate("19", "March", "1990")
                .setSubject("Chemistry")
                .setSHobbies("Reading")
                .uploadPicture("src/test/resources/img.JPG")
                .setCurrentAddress("Current address")
                .setStateCity("Uttar Pradesh", "Agra")
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", "Elina Kim")
                .checkResult("Student Email", "Elina@yandex.ru")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "8987822639")
                .checkResult("Date of Birth", "19 March,1990")
                .checkResult("Subjects", "Chemistry")
                .checkResult("Hobbies", "Reading")
                .checkResult("Picture", "img.JPG")
                .checkResult("Address", "Current address")
                .checkResult("State and City", "Uttar Pradesh Agra");

    }

    @Test
    void fillFormWithMinimumDataTest() {
        registrationFormPage.openPage()
                .setFirstName("Elina")
                .setLastName("Kim")
                .setGender("Female")
                .setUserNumber("8987822639")
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", "Elina Kim")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "8987822639");

    }
}

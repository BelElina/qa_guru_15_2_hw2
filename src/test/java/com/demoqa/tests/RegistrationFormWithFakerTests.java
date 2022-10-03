package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
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


        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", "Female")
                .checkResult("Mobile", phone)
                .checkResult("Date of Birth", date)
                .checkResult("Subjects", "Chemistry")
                .checkResult("Hobbies", "Reading")
                .checkResult("Picture", "img.JPG")
                .checkResult("Address", currentAddress)
                .checkResult("State and City", "Uttar Pradesh Agra");
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

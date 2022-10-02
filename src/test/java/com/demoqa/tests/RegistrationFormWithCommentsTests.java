package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormWithCommentsTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").setValue("Elina");
        $("#lastName").setValue("Kim");
        $("#userEmail").setValue("Elina@yandex.ru");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("8987822639");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--019:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/img.JPG"));
        $("#currentAddress").setValue("Current address");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Agra")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        //$(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Elina Kim"));
        //$(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("19 March,1990"));
        $(".table-responsive").shouldHave(text("Elina Kim"),
                text("Elina@yandex.ru"),
                text("Female"),
                text("8987822639"),
                text("19 March,1990"),
                text("Chemistry"),
                text("Reading"),
                text("img.JPG"),
                text("Current address"),
                text("Uttar Pradesh Agra"));

        // $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Elina Kim"));

        $("#closeLargeModal").click();
    }
}

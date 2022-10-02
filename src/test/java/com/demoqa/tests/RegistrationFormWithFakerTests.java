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
import static utils.RandomUtils.*;

public class RegistrationFormWithFakerTests {

    String firstName = getRandomString(10);
    String lastName = getRandomString(10);
    String email = getRandomEmail();
    String currentAddress = getRandomAlphabetics(20);
    String phone = getRandomPhone();
    String day = "19";
    String month = "March";
    String year = "1990";


    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTests() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day +":not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/img.JPG"));
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Agra")).click();
        $("#submit").click();

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
                text("Current address"),
                text("Uttar Pradesh Agra"));
    }
}

package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static String firstName = "Elina",
            lastName = "Kim",
            email = "Elina@yandex.ru",
            phone = "8987822639",
            day = "19",
            month = "March",
            year = "1990";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

}

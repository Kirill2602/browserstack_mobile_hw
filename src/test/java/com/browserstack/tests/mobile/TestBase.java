package com.browserstack.tests.mobile;

import com.browserstack.drivers.BrowserstackDrivers;
import com.browserstack.drivers.MobileDriver;
import com.browserstack.helpers.Attach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sessionId;

public class TestBase {
    @BeforeAll

    static void beforeAll() {
        switch (System.getProperty("env")) {
            case "android":
            case "ios":
                Configuration.browser = BrowserstackDrivers.class.getName();
                break;
            case "mobile":
                Configuration.browser = MobileDriver.class.getName();
                break;
        }
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 15000;
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = sessionId().toString();
        Attach.pageSource();
        Selenide.closeWebDriver();
        if (!System.getProperty("env").equals("mobile")) Attach.addVideo(sessionId);
    }
}

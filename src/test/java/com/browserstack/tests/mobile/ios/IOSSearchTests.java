package com.browserstack.tests.mobile.ios;

import com.browserstack.tests.mobile.TestBase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.id;

@Tag("ios")
public class IOSSearchTests extends TestBase {
    @Test
    void checkOutputTextTest() {
        step("Click Text Button", () -> {
            $(id("Text Button")).click();
        });

        step("Check initial state Output text", () -> {
            assertEquals("Waiting for text input.", $(id("Text Output")).getText());
        });

        step(format("Set value %s in the input field and press enter", "hello@browserstack.com"), () -> {
            $(id("Text Input")).click();
            $(id("Text Input")).sendKeys("hello@browserstack.com");
            $(id("Text Input")).pressEnter();
        });

        step("Check Output text", () -> {
            assertEquals("hello@browserstack.com", $(id("Text Output")).getText());
        });
    }
}

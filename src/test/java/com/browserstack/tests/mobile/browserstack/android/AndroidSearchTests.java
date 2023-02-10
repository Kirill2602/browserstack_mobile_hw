package com.browserstack.tests.mobile.browserstack.android;

import com.browserstack.tests.mobile.TestBase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.id;

public class AndroidSearchTests extends TestBase {

    @Test
    @Tag("android")
    void searchTest() {
        back();
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android")
    void onBoardingScreenTest() {
        step("Go to main page", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia\n" +
                    "…in over 300 languages"));
        });
        step("Go to New ways to explore page", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            assertEquals("New ways to explore", $(id("org.wikipedia.alpha:id/primaryTextView")).text());

        });
        step("Go to  Reading lists with sync page", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            assertEquals("Reading lists with sync", $(id("org.wikipedia.alpha:id/primaryTextView")).text());
        });
        step("Go to Send anonymous data page", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            assertEquals("Send anonymous data", $(id("org.wikipedia.alpha:id/primaryTextView")).text());
        });
        step("Ending onBoarding", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();
            $(id("org.wikipedia.alpha:id/main_toolbar_wordmark")).shouldBe(visible);
        });
    }

    @Test
    @Tag("android")
    void goToTheArticleTest() {
        back();
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenium");
        });

        step("Click on the selenium article", () -> {
            $(id("org.wikipedia.alpha:id/page_list_item_title")).click();
        });

        step("Click Find in article", () -> {
            $(accessibilityId("Find in article")).click();
        });

        step("Set value in text input", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("selenium");
        });

        step("Check article text", () -> {
            assertEquals("Selenium (software)", $x("//android.view.View[@content-desc='Selenium (software)']/android.widget.TextView").text());
        });
    }
}

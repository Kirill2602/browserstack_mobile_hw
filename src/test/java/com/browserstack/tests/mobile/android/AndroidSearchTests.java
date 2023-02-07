package com.browserstack.tests.mobile.android;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

@Tag("android")
public class AndroidSearchTests extends TestBase {

    @Test
    void searchTest() {
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
    void goToTheArticleTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenium");
        });
        step("Click on the selenium article", () -> {
            $(id("org.wikipedia.alpha:id/fragment_feed_header")).click();
        });
        step("Click on the back button", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_button")).click();
        });
        step("Check visibility of header logo", () ->
                $(id("org.wikipedia.alpha:id/single_fragment_toolbar_wordmark"))
                        .shouldBe(visible));

    }
}

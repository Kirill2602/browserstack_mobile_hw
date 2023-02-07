package com.browserstack.drivers;

import com.browserstack.config.Config;
import com.codeborne.selenide.WebDriverProvider;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDrivers implements WebDriverProvider {
    static Config config = ConfigFactory.create(Config.class);

    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

//        mutableCapabilities.setCapability("app", "bs://444bd0308813ae0dc236f8cd461c02d3afa7901d");
//        mutableCapabilities.setCapability("device", "iPhone 14 Pro Max");
//        mutableCapabilities.setCapability("os_version", "16");
//        mutableCapabilities.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");
//        mutableCapabilities.setCapability("device", "Google Pixel 3");
//        mutableCapabilities.setCapability("os_version", "9.0");
        mutableCapabilities.setCapability("app", config.appUrl());
        mutableCapabilities.setCapability("devise", config.device());
        mutableCapabilities.setCapability("os_version", config.osVersion());

        // Set your access credentials
//        mutableCapabilities.setCapability("browserstack.user", "kirill530");
//        mutableCapabilities.setCapability("browserstack.key", "zyzzGhYFCmx6eB18fu3P");
        mutableCapabilities.setCapability("browserstack.user", config.login());
        mutableCapabilities.setCapability("browserstack.key", config.password());

        // Set URL of the application under test
//        mutableCapabilities.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");
//        mutableCapabilities.setCapability("app", "bs://444bd0308813ae0dc236f8cd461c02d3afa7901d");
        // Specify device and os_version for testing
//        mutableCapabilities.setCapability("device", "Google Pixel 3");
//        mutableCapabilities.setCapability("os_version", "9.0");
//        mutableCapabilities.setCapability("device", "iPhone 14 Pro Max");
//        mutableCapabilities.setCapability("os_version", "16");
        // Set other BrowserStack capabilities
//        mutableCapabilities.setCapability("project", "First Java Project");
//        mutableCapabilities.setCapability("build", "browserstack-build-1");
//        mutableCapabilities.setCapability("name", "first_test");
        mutableCapabilities.setCapability("project", config.projectName());
        mutableCapabilities.setCapability("build", config.buildName());
        mutableCapabilities.setCapability("name", config.testName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
//        return new RemoteWebDriver(new URL("http://hub.browserstack.com/wd/hub"), mutableCapabilities);
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);

    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(config.baseUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

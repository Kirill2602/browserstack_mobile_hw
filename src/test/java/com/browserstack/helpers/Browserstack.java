package com.browserstack.helpers;

import com.browserstack.config.Config;
import org.aeonbits.owner.ConfigFactory;

import static com.browserstack.helpers.CustomListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {
    public static String getVideoUrl(String sessionId) {
        Config config = ConfigFactory.create(Config.class);
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
        return given()
                .log().all()
                .filter(withCustomTemplates())
                .auth().basic(config.login(), config.password())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}

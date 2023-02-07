package com.browserstack.helpers;

import static com.browserstack.helpers.CustomListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {
    public static String getVideoUrl(String sessionId) {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
        return given()
                .log().all()
                .filter(withCustomTemplates())
                .auth().basic("kirill530", "zyzzGhYFCmx6eB18fu3P")
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}

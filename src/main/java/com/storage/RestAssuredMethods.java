package com.storage;

import io.restassured.response.Response;

import static com.storage.SettingsInterface.APPLICATION_JSON;
import static com.storage.SettingsInterface.CONTENT_TYPE;
import static io.restassured.RestAssured.given;

public class RestAssuredMethods {
    public static Response sendByPost(String url, Object body){
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .and()
                .body(body)
                .when()
                .post(url);
    }

    public static void checkResponseStatusCode(Response response, int statusCode){
        response.then().statusCode(statusCode);
    }

    public static void checkResponseBodyFor(Response response, int statusCode){
        response.then().statusCode(statusCode);
    }
}

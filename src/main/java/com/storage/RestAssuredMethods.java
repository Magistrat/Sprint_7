package com.storage;

import io.restassured.response.Response;

import static com.storage.SettingsInterface.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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

    public static void checkResponseBodyForCourierPositive(Response response){
        response.then().assertThat().body("ok", equalTo(true));
    }

    public static void checkResponseBodyForCourierNegative(Response response){
        response.then().assertThat()
                .body("code", equalTo(BAD_REQUEST_STATUS_CODE))
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    public static void checkResponseBodyForCourierNegativeAlreadyExists(Response response){
        response.then().assertThat()
                .body("code", equalTo(CONFLICT_STATUS_CODE))
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}

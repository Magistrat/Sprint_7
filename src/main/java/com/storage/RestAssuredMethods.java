package com.storage;

import io.restassured.response.Response;

import java.util.List;

import static com.storage.SettingsInterface.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;

public class RestAssuredMethods {
    public static Response sendByPost(String url, Object body){
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .and()
                .body(body)
                .when()
                .post(url);
    }

    public static Response sendByGetWithEmptyBody(String url){
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get(url);
    }

    public static void checkResponseStatusCode(Response response, int statusCode){
        response.then().statusCode(statusCode);
    }

    public static void checkResponseBodyForCourierCreatePositive(Response response){
        response.then().assertThat().body("ok", equalTo(true));
    }

    public static void checkResponseBodyForCourierCreateNegative(Response response){
        response.then().assertThat()
                .body("code", equalTo(BAD_REQUEST_STATUS_CODE))
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    public static void checkResponseBodyForCourierCreateNegativeAlreadyExists(Response response){
        response.then().assertThat()
                .body("code", equalTo(CONFLICT_STATUS_CODE))
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    public static void checkResponseBodyForCourierLoginPositive(Response response){
        response.then().assertThat()
                .body("id", notNullValue())
                .body("id", is(instanceOf(Number.class)));
    }

    public static void checkResponseBodyForCourierLoginNegativeWrongPasswordAndLogin(Response response){
        response.then().assertThat()
                .body("code", equalTo(NOT_FOUND_STATUS_CODE))
                .body("message", equalTo("Учетная запись не найдена"));
    }

    public static void checkResponseBodyForCourierLoginNegativeWithoutPasswordAndLogin(Response response){
        response.then().assertThat()
                .body("code", equalTo(BAD_REQUEST_STATUS_CODE))
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    public static void checkResponseBodyForOrderCreatePositive(Response response){
        response.then().assertThat()
                .body("track", notNullValue())
                .body("track", is(instanceOf(Number.class)));
    }

    public static void checkResponseBodyForOrderList(Response response){
        response.then().assertThat()
                .body("orders", notNullValue())
                .body("orders", is(instanceOf(List.class)));
    }
}

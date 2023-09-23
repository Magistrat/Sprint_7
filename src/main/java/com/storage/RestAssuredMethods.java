package com.storage;

import com.storage.pojo.courier.login.LoginCourierResponsePojo;
import com.storage.pojo.order.cancel.CancelOrderPojo;
import com.storage.pojo.order.create.CreateOrderResponsePojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static com.storage.SettingsInterface.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;

public class RestAssuredMethods {
    @Step("Отправка запроса на API методом POST")
    public static Response sendByPost(String url, Object body){
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .and()
                .body(body)
                .when()
                .post(url);
    }

    @Step("Отправка запроса на API методом GET (без тела запроса)")
    public static Response sendByGetWithEmptyBody(String url){
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get(url);
    }

    @Step("Login. Десериализация ответа")
    public static LoginCourierResponsePojo loginResponseDeserialization(String url, Object body){
        return sendByPost(url, body).as(LoginCourierResponsePojo.class);
    }

    @Step("Удаление Курьера по ID")
    public static void sendByDeleteWithParamId(String url){
        given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .delete(url);
    }

    @Step("Получение track и отмена заказа")
    public static void cancelOrderByResponse(Response response){
        given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .and()
                .body(new CancelOrderPojo(response.as(CreateOrderResponsePojo.class).getTrack()))
                .when()
                .put(CANCEL_ORDER_URL);
    }

    @Step("Проверка статус кода с ожидаемым")
    public static void checkResponseStatusCode(Response response, int statusCode){
        response.then().statusCode(statusCode);
    }

    @Step("Успешно создан Курьер")
    public static void checkResponseBodyForCourierCreatePositive(Response response){
        response.then().assertThat().body("ok", equalTo(true));
    }

    @Step("Недостаточно данных для создания учетной записи")
    public static void checkResponseBodyForCourierCreateNegative(Response response){
        response.then().assertThat()
                .body("code", equalTo(BAD_REQUEST_STATUS_CODE))
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Этот логин уже используется")
    public static void checkResponseBodyForCourierCreateNegativeAlreadyExists(Response response){
        response.then().assertThat()
                .body("code", equalTo(CONFLICT_STATUS_CODE))
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Успешная аутентификация курьера")
    public static void checkResponseBodyForCourierLoginPositive(Response response){
        response.then().assertThat()
                .body("id", notNullValue())
                .body("id", is(instanceOf(Number.class)));
    }

    @Step("Учетная запись не найдена")
    public static void checkResponseBodyForCourierLoginNegativeWrongPasswordAndLogin(Response response){
        response.then().assertThat()
                .body("code", equalTo(NOT_FOUND_STATUS_CODE))
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Недостаточно данных для входа")
    public static void checkResponseBodyForCourierLoginNegativeWithoutPasswordAndLogin(Response response){
        response.then().assertThat()
                .body("code", equalTo(BAD_REQUEST_STATUS_CODE))
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Создан заказ с заданым цветом")
    public static void checkResponseBodyForOrderCreatePositive(Response response){
        response.then().assertThat()
                .body("track", notNullValue())
                .body("track", is(instanceOf(Number.class)));
    }

    @Step("Получен список заказов (без тела при запросе)")
    public static void checkResponseBodyForOrderList(Response response){
        response.then().assertThat()
                .body("orders", notNullValue())
                .body("orders", is(instanceOf(List.class)));
    }
}

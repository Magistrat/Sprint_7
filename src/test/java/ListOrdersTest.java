import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static com.storage.RestAssuredMethods.*;
import static com.storage.SettingsInterface.*;

public class ListOrdersTest {
    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Получение списка заказов без Тела при запросе")
    public void getListOrdersWithEmptyBody(){
        Response response = sendByGetWithEmptyBody(ORDERS_URL);
        checkResponseStatusCode(response, SUCCESS_STATUS_CODE);
        checkResponseBodyForOrderList(response);
    }
}

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
    public void getListOrdersWithEmptyBody(){
        Response response = sendByGetWithEmptyBody(GET_ORDERS_URL);
        checkResponseStatusCode(response, SUCCESS_STATUS_CODE);
        checkResponseBodyForOrderList(response);
    }
}

import com.storage.pojo.order.create.PositiveCreateOrderPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static com.storage.RestAssuredMethods.*;
import static com.storage.SettingsInterface.*;

@RunWith(Parameterized.class)
public class CreatingOrderTest {

    @Parameterized.Parameters
    public static Object[][] testData(){
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of()},
        };
    }
    private final PositiveCreateOrderPojo createOrder;

    public CreatingOrderTest(List<String> colors){
        this.createOrder = new PositiveCreateOrderPojo(
                "firstName",
                "lastName",
                "address",
                "metroStation",
                "phone",
                2,
                "2020-06-06",
                "comment",
                colors
        );
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createOrderWithAllColors(){
        System.out.println();
        Response response = sendByPost(CREATED_ORDER_URL, createOrder);
        checkResponseStatusCode(response, CREATED_STATUS_CODE);
        checkResponseBodyForOrderCreatePositive(response);
    }
}

import com.storage.pojo.order.create.PositiveCreateOrderPojo;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static com.storage.SettingsInterface.BASE_URL;

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
                "rentTime",
                "deliveryDate",
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

    }
}

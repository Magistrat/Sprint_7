import com.storage.pojo.courier.create.PositiveCourierRequestPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static com.storage.Base.generateTestData;
import static com.storage.RestAssuredMethods.*;
import static com.storage.SettingsInterface.*;

public class CreatingTwoCouriersTest {
    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createTwoCouriersWithSameData(){
        PositiveCourierRequestPojo positiveCourier = new PositiveCourierRequestPojo(
                generateTestData("login"),
                generateTestData("password"),
                generateTestData("firstName")
        );

        sendByPost(CREATED_COURIER_URL, positiveCourier);
        Response response = sendByPost(CREATED_COURIER_URL, positiveCourier);

        checkResponseStatusCode(response, CONFLICT_STATUS_CODE);
        checkResponseBodyForCourierNegativeAlreadyExists(response);
    }
}

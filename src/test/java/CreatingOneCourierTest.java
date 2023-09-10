import com.storage.CreatingCourierRequestPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static com.storage.Base.generateTestData;
import static com.storage.RestAssuredMethods.*;
import static com.storage.SettingsInterface.BASE_URL;
import static com.storage.SettingsInterface.CREATED_COURIER_URL;
import static com.storage.SettingsInterface.CREATED_STATUS_CODE;


public class CreatingOneCourierTest {

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createCourierPositiveTest(){
        CreatingCourierRequestPojo positiveCourier = new CreatingCourierRequestPojo(
                generateTestData("login"),
                generateTestData("password"),
                generateTestData("firstName")
        );

        Response response = sendByPost(CREATED_COURIER_URL, positiveCourier);
        checkResponseStatusCode(response, CREATED_STATUS_CODE);
        checkResponseBodyForCourier(response);
    }

}

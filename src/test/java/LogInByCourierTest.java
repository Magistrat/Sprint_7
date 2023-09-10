import com.storage.pojo.courier.create.PositiveCreateCourierRequestPojo;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static com.storage.Base.generateTestData;
import static com.storage.RestAssuredMethods.sendByPost;
import static com.storage.SettingsInterface.BASE_URL;
import static com.storage.SettingsInterface.CREATED_COURIER_URL;

public class LogInByCourierTest {
    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;

        PositiveCreateCourierRequestPojo positiveCourier = new PositiveCreateCourierRequestPojo(
                generateTestData("login"),
                generateTestData("password"),
                generateTestData("firstName")
        );
        sendByPost(CREATED_COURIER_URL, positiveCourier);
    }

    @Test
    public void loginByCourierPositive(){

    }





}

import com.storage.pojo.courier.create.PositiveCreateCourierRequestPojo;
import com.storage.pojo.courier.login.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static com.storage.Base.generateTestData;
import static com.storage.RestAssuredMethods.*;
import static com.storage.SettingsInterface.*;

public class LogInByCourierTest {
    private PositiveCreateCourierRequestPojo positiveCourier;

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;

        positiveCourier = new PositiveCreateCourierRequestPojo(
                generateTestData("login"),
                generateTestData("password"),
                generateTestData("firstName")
        );
        sendByPost(CREATED_COURIER_URL, positiveCourier);
    }

    @Test
    public void loginByCourierPositive(){
        PositiveLoginCourierRequestPojo positiveLogin = new PositiveLoginCourierRequestPojo(
                positiveCourier.getLogin(),
                positiveCourier.getPassword()
        );

        Response response = sendByPost(LOG_IN_BY_COURIER_URL, positiveLogin);
        checkResponseStatusCode(response, SUCCESS_STATUS_CODE);
        checkResponseBodyForCourierLoginPositive(response);
    }
}

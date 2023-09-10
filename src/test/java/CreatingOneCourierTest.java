import com.storage.pojo.courier.create.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static com.storage.Base.generateTestData;
import static com.storage.RestAssuredMethods.*;
import static com.storage.SettingsInterface.*;


public class CreatingOneCourierTest {

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createCourierPositiveTest(){
        PositiveCourierRequestPojo positiveCourier = new PositiveCourierRequestPojo(
                generateTestData("login"),
                generateTestData("password"),
                generateTestData("firstName")
        );

        Response response = sendByPost(CREATED_COURIER_URL, positiveCourier);
        checkResponseStatusCode(response, CREATED_STATUS_CODE);
        checkResponseBodyForCourierPositive(response);
    }

    @Test
    public void createCourierNegativeWithoutFirstNameTest(){
        NegativeWithoutFirstNameCourierRequestPojo negativeWithoutFirstName = new NegativeWithoutFirstNameCourierRequestPojo(
                generateTestData("login"),
                generateTestData("password")
        );

        Response response = sendByPost(CREATED_COURIER_URL, negativeWithoutFirstName);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierNegative(response);
    }

    @Test
    public void createCourierNegativeWithoutLoginTest(){
        NegativeWithoutLoginCourierRequestPojo negativeWithoutLogin = new NegativeWithoutLoginCourierRequestPojo(
                generateTestData("password"),
                generateTestData("firstName")
        );

        Response response = sendByPost(CREATED_COURIER_URL, negativeWithoutLogin);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierNegative(response);
    }

    @Test
    public void createCourierNegativeWithoutPasswordTest(){
        NegativeWithoutPasswordCourierRequestPojo negativeWithoutPassword = new NegativeWithoutPasswordCourierRequestPojo(
                generateTestData("login"),
                generateTestData("firstName")
        );

        Response response = sendByPost(CREATED_COURIER_URL, negativeWithoutPassword);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierNegative(response);
    }

}

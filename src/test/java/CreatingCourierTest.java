import com.storage.pojo.courier.create.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static com.storage.Base.generateTestData;
import static com.storage.RestAssuredMethods.*;
import static com.storage.SettingsInterface.*;


public class CreatingCourierTest {

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createCourierPositiveTest(){
        PositiveCreateCourierRequestPojo positiveCourier = new PositiveCreateCourierRequestPojo(
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
        NegativeWithoutFirstNameCreateCourierRequestPojo negativeWithoutFirstName = new NegativeWithoutFirstNameCreateCourierRequestPojo(
                generateTestData("login"),
                generateTestData("password")
        );

        Response response = sendByPost(CREATED_COURIER_URL, negativeWithoutFirstName);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierNegative(response);
    }

    @Test
    public void createCourierNegativeWithoutLoginTest(){
        NegativeWithoutLoginCreateCourierRequestPojo negativeWithoutLogin = new NegativeWithoutLoginCreateCourierRequestPojo(
                generateTestData("password"),
                generateTestData("firstName")
        );

        Response response = sendByPost(CREATED_COURIER_URL, negativeWithoutLogin);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierNegative(response);
    }

    @Test
    public void createCourierNegativeWithoutPasswordTest(){
        NegativeWithoutPasswordCreateCourierRequestPojo negativeWithoutPassword = new NegativeWithoutPasswordCreateCourierRequestPojo(
                generateTestData("login"),
                generateTestData("firstName")
        );

        Response response = sendByPost(CREATED_COURIER_URL, negativeWithoutPassword);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierNegative(response);
    }
    @Test
    public void createTwoCouriersWithSameData() {
        PositiveCreateCourierRequestPojo positiveCourier = new PositiveCreateCourierRequestPojo(
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

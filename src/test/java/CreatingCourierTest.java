import com.storage.pojo.courier.create.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static com.storage.Base.generateTestData;
import static com.storage.RestAssuredMethods.*;
import static com.storage.SettingsInterface.*;


public class CreatingCourierTest {
    private String generatedTestLogin;
    private String generatedTestPassword;
    private String generatedTestFirstName;

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
        generatedTestLogin = generateTestData("login");
        generatedTestPassword = generateTestData("password");
        generatedTestFirstName = generateTestData("firstName");
    }

    @Test
    public void createCourierPositiveTest(){
        PositiveCreateCourierRequestPojo positiveCourier = new PositiveCreateCourierRequestPojo(
                generatedTestLogin,
                generatedTestPassword,
                generatedTestFirstName
        );

        Response response = sendByPost(CREATED_COURIER_URL, positiveCourier);
        checkResponseStatusCode(response, CREATED_STATUS_CODE);
        checkResponseBodyForCourierCreatePositive(response);
    }

    @Test
    public void createCourierNegativeWithoutFirstNameTest(){
        NegativeWithoutFirstNameCreateCourierRequestPojo negativeWithoutFirstName = new NegativeWithoutFirstNameCreateCourierRequestPojo(
                generatedTestLogin,
                generatedTestPassword
        );

        Response response = sendByPost(CREATED_COURIER_URL, negativeWithoutFirstName);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierCreateNegative(response);
    }

    @Test
    public void createCourierNegativeWithoutLoginTest(){
        NegativeWithoutLoginCreateCourierRequestPojo negativeWithoutLogin = new NegativeWithoutLoginCreateCourierRequestPojo(
                generatedTestPassword,
                generatedTestFirstName
        );

        Response response = sendByPost(CREATED_COURIER_URL, negativeWithoutLogin);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierCreateNegative(response);
    }

    @Test
    public void createCourierNegativeWithoutPasswordTest(){
        NegativeWithoutPasswordCreateCourierRequestPojo negativeWithoutPassword = new NegativeWithoutPasswordCreateCourierRequestPojo(
                generatedTestLogin,
                generatedTestFirstName
        );

        Response response = sendByPost(CREATED_COURIER_URL, negativeWithoutPassword);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierCreateNegative(response);
    }
    @Test
    public void createTwoCouriersWithSameData() {
        PositiveCreateCourierRequestPojo positiveCourier = new PositiveCreateCourierRequestPojo(
                generatedTestLogin,
                generatedTestPassword,
                generatedTestFirstName
        );

        sendByPost(CREATED_COURIER_URL, positiveCourier);
        Response response = sendByPost(CREATED_COURIER_URL, positiveCourier);

        checkResponseStatusCode(response, CONFLICT_STATUS_CODE);
        checkResponseBodyForCourierCreateNegativeAlreadyExists(response);
    }

}

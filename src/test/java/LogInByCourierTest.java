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
    public void loginByCourierPositiveTest(){
        PositiveLogInCourierRequestPojo positiveLogIn = new PositiveLogInCourierRequestPojo(
                positiveCourier.getLogin(),
                positiveCourier.getPassword()
        );

        Response response = sendByPost(LOG_IN_BY_COURIER_URL, positiveLogIn);
        checkResponseStatusCode(response, SUCCESS_STATUS_CODE);
        checkResponseBodyForCourierLoginPositive(response);
    }

    @Test
    public void loginByCourierNegativeWrongPasswordTest(){
        PositiveLogInCourierRequestPojo negativeWrongPassword = new PositiveLogInCourierRequestPojo(
                positiveCourier.getLogin(),
                positiveCourier.getPassword() + positiveCourier.getPassword()
        );

        Response response = sendByPost(LOG_IN_BY_COURIER_URL, negativeWrongPassword);
        checkResponseStatusCode(response, NOT_FOUND_STATUS_CODE);
        checkResponseBodyForCourierLoginNegativeWrongPasswordAndLogin(response);
    }

    @Test
    public void loginByCourierNegativeWrongLoginTest(){
        PositiveLogInCourierRequestPojo negativeWrongLogin = new PositiveLogInCourierRequestPojo(
                positiveCourier.getLogin() + positiveCourier.getLogin(),
                positiveCourier.getPassword()
        );

        Response response = sendByPost(LOG_IN_BY_COURIER_URL, negativeWrongLogin);
        checkResponseStatusCode(response, NOT_FOUND_STATUS_CODE);
        checkResponseBodyForCourierLoginNegativeWrongPasswordAndLogin(response);
    }

    @Test
    public void loginByCourierNegativeWithoutLoginTest(){
        NegativeWithoutLoginLogInCourierRequestPojo negativeWithoutLogin =
                new NegativeWithoutLoginLogInCourierRequestPojo(
                    positiveCourier.getPassword()
                );

        Response response = sendByPost(LOG_IN_BY_COURIER_URL, negativeWithoutLogin);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierLoginNegativeWithoutPasswordAndLogin(response);
    }

    @Test
    public void loginByCourierNegativeWithoutPasswordTest(){
        NegativeWithoutPasswordLogInCourierRequestPojo negativeWithoutPassword =
                new NegativeWithoutPasswordLogInCourierRequestPojo(
                        positiveCourier.getLogin()
                );

        Response response = sendByPost(LOG_IN_BY_COURIER_URL, negativeWithoutPassword);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierLoginNegativeWithoutPasswordAndLogin(response);
    }

}

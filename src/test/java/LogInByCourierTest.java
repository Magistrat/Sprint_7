import com.storage.pojo.courier.create.PositiveCreateCourierRequestPojo;
import com.storage.pojo.courier.login.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
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
        sendByPost(COURIER_URL, positiveCourier);
    }

    @Test
    @DisplayName("Аутентификация курьера")
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
    @DisplayName("Негативный. Аутентификация курьера с неправильно указанным паролем")
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
    @DisplayName("Негативный. Аутентификация курьера с неправильно указанным логином")
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
    @DisplayName("Негативный. Аутентификация курьера без логина")
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
    @DisplayName("Негативный. Аутентификация курьера без пароля")
    @Description("Падает по причине бага в API")
    public void loginByCourierNegativeWithoutPasswordTest(){
        NegativeWithoutPasswordLogInCourierRequestPojo negativeWithoutPassword =
                new NegativeWithoutPasswordLogInCourierRequestPojo(
                        positiveCourier.getLogin()
                );

        Response response = sendByPost(LOG_IN_BY_COURIER_URL, negativeWithoutPassword);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierLoginNegativeWithoutPasswordAndLogin(response);
    }

    @After
    public void deleteCourier(){
        PositiveLogInCourierRequestPojo positiveLogIn = new PositiveLogInCourierRequestPojo(
                positiveCourier.getLogin(),
                positiveCourier.getPassword()
        );
        sendByDeleteWithParamId(
                COURIER_URL + "/" + loginResponseDeserialization(LOG_IN_BY_COURIER_URL, positiveLogIn).getId()
        );
    }

}

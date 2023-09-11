import com.storage.pojo.courier.create.*;
import com.storage.pojo.courier.login.PositiveLogInCourierRequestPojo;
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
    @DisplayName("Создание Курьера")
    public void createCourierPositiveTest(){
        PositiveCreateCourierRequestPojo positiveCourier = new PositiveCreateCourierRequestPojo(
                generatedTestLogin,
                generatedTestPassword,
                generatedTestFirstName
        );

        Response response = sendByPost(COURIER_URL, positiveCourier);
        checkResponseStatusCode(response, CREATED_STATUS_CODE);
        checkResponseBodyForCourierCreatePositive(response);
    }

    @Test
    @DisplayName("Негативный. Создание Курьера без FirstName")
    @Description("Падает по причине бага в API")
    public void createCourierNegativeWithoutFirstNameTest(){
        NegativeWithoutFirstNameCreateCourierRequestPojo negativeWithoutFirstName = new NegativeWithoutFirstNameCreateCourierRequestPojo(
                generatedTestLogin,
                generatedTestPassword
        );

        Response response = sendByPost(COURIER_URL, negativeWithoutFirstName);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierCreateNegative(response);
    }

    @Test
    @DisplayName("Негативный. Создание Курьера без Login")
    public void createCourierNegativeWithoutLoginTest(){
        NegativeWithoutLoginCreateCourierRequestPojo negativeWithoutLogin = new NegativeWithoutLoginCreateCourierRequestPojo(
                generatedTestPassword,
                generatedTestFirstName
        );

        Response response = sendByPost(COURIER_URL, negativeWithoutLogin);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierCreateNegative(response);
    }

    @Test
    @DisplayName("Негативный. Создание Курьера без Password")
    public void createCourierNegativeWithoutPasswordTest(){
        NegativeWithoutPasswordCreateCourierRequestPojo negativeWithoutPassword = new NegativeWithoutPasswordCreateCourierRequestPojo(
                generatedTestLogin,
                generatedTestFirstName
        );

        Response response = sendByPost(COURIER_URL, negativeWithoutPassword);
        checkResponseStatusCode(response, BAD_REQUEST_STATUS_CODE);
        checkResponseBodyForCourierCreateNegative(response);
    }
    @Test
    @DisplayName("Негативный. Создание Курьера с данными существующего курера")
    public void createTwoCouriersWithSameData() {
        PositiveCreateCourierRequestPojo positiveCourier = new PositiveCreateCourierRequestPojo(
                generatedTestLogin,
                generatedTestPassword,
                generatedTestFirstName
        );

        sendByPost(COURIER_URL, positiveCourier);
        Response response = sendByPost(COURIER_URL, positiveCourier);

        checkResponseStatusCode(response, CONFLICT_STATUS_CODE);
        checkResponseBodyForCourierCreateNegativeAlreadyExists(response);
    }

    @After
    public void deleteCourier(){
        PositiveLogInCourierRequestPojo positiveLogIn = new PositiveLogInCourierRequestPojo(
                generatedTestLogin,
                generatedTestPassword
        );
        sendByDeleteWithParamId(
                COURIER_URL + "/" + loginResponseDeserialization(LOG_IN_BY_COURIER_URL, positiveLogIn).getId()
        );
    }

}

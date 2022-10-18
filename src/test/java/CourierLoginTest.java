import client.CourierClient;
import dto.CourierRequest;
import dto.LoginRequest;
import generator.LoginRequestGenerator;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static generator.CourierRequestGenerator.getRandomCourier;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest {

    private CourierClient courierClient;

    private Integer id;


    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                    .assertThat()
                    .body("ok", equalTo(true));
        }
    }

    @Test // Проверки входа без логина и без пароля
    public void courierShouldNotBeLogged() {
        // попытка входа без логина
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(null);
        loginRequest.setPassword("12345");
        courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
        // попытка входа без пароля
        // (тест фэйлится если не вводить пароль, ошибка 504)
        loginRequest.setLogin("TestLogin12345");
        loginRequest.setPassword(null);
        courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test // Проверка валидации поля login
    public void loginFieldShouldBeValidated() {

        CourierRequest randomCourier = getRandomCourier();

        courierClient.create(randomCourier)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));

        LoginRequest loginRequest = LoginRequestGenerator.from(randomCourier);

        id = courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", Matchers.notNullValue())
                .extract()
                .path("id");

        loginRequest.setLogin("invalid_login");
        courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

   @Test // Проверка валидации поля password
    public void passwordFieldShouldBeValidated() {
       CourierRequest randomCourier = getRandomCourier();

       courierClient.create(randomCourier)
               .assertThat()
               .statusCode(SC_CREATED)
               .and()
               .body("ok", equalTo(true));

       LoginRequest loginRequest = LoginRequestGenerator.from(randomCourier);

       id = courierClient.login(loginRequest)
               .assertThat()
               .statusCode(SC_OK)
               .and()
               .body("id", Matchers.notNullValue())
               .extract()
               .path("id");

       loginRequest.setPassword("invalid_password");
       courierClient.login(loginRequest)
               .assertThat()
               .statusCode(SC_NOT_FOUND)
               .and()
               .body("message", equalTo("Учетная запись не найдена"));
    }
}

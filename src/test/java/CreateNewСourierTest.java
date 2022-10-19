import client.CourierClient;
import dto.CourierRequest;
import dto.LoginRequest;
import generator.LoginRequestGenerator;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static generator.CourierRequestGenerator.getRandomCourier;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;


public class CreateNewСourierTest {

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

    @Test
    @DisplayName("Creating a valid courier")
    @Description("Positive test of api /api/v1/courier endpoint")
    public void courierShouldBeCreated() {

        CourierRequest randomCourier = getRandomCourier();

        courierClient.create(randomCourier)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));

        courierClient.create(randomCourier)
                .assertThat()
                .statusCode(SC_CONFLICT)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

            LoginRequest loginRequest = LoginRequestGenerator.from(randomCourier);

            id = courierClient.login(loginRequest)
                    .assertThat()
                    .statusCode(SC_OK)
                    .and()
                    .body("id", Matchers.notNullValue())
                    .extract()
                    .path("id");
    }

    @Test
    @DisplayName("Creating an invalid courier without password")
    @Description("Negative test of api /api/v1/courier endpoint")
    public void courierShouldNotBeCreated() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(11));
        courierRequest.setPassword(null);
        courierClient.create(courierRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}

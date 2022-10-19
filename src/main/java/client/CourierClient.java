package client;

import dto.CourierRequest;
import dto.LoginRequest;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class CourierClient extends RestClient {

    public static final String COURIER = "/api/v1/courier";
    public static final String COURIER_LOGIN = "/api/v1/courier/login";
    public static final String COURIER_DELETE = "/api/v1/courier/{id}";

    @Step
    public ValidatableResponse create(CourierRequest courierRequest) {
      return  given()
              .spec(getDefaultRequestSpec())
              .body(courierRequest)
              .post(COURIER)
              .then();
    }

    @Step
    public ValidatableResponse login(LoginRequest loginRequest) {
        return  given()
                .spec(getDefaultRequestSpec())
                .body(loginRequest)
                .post(COURIER_LOGIN)
                .then();

    }

    @Step
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getDefaultRequestSpec())
                .delete(COURIER_DELETE, id)
                .then();
    }
}

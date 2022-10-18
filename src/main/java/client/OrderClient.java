package client;

import dto.OrderRequest;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {

    public static final String ORDER = "/api/v1/orders";

    @Step
    public ValidatableResponse create(OrderRequest orderRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(orderRequest)
                .post(ORDER)
                .then();
    }
}

package client;

import dto.OrderRequest;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {

    public static final String ORDER = "/api/v1/orders";

    public static final String ORDERBYTRACK = "/v1/orders/track?t={track}";

    @Step
    public ValidatableResponse create(OrderRequest orderRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(orderRequest)
                .post(ORDER)
                .then();
    }

    @Step
    public ValidatableResponse getOrders(int track) {
        return given()
                .spec(getDefaultRequestSpec())
                .get(ORDERBYTRACK, track)
                .then();
    }
}

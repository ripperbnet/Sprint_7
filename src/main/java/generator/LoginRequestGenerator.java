package generator;

import dto.CourierRequest;
import dto.LoginRequest;

public class LoginRequestGenerator {
    public static LoginRequest from(CourierRequest courierRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(courierRequest.getLogin());
        loginRequest.setPassword(courierRequest.getPassword());
        return loginRequest;
    }

    public static LoginRequest loginWithNullLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(null);
        loginRequest.setPassword("12345");
        return loginRequest;
    }

    public static LoginRequest loginWithNullPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin("TestLogin123");
        loginRequest.setPassword(null);
        return loginRequest;
    }
}
package generator;

import dto.CourierRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierRequestGenerator {
    public static CourierRequest getRandomCourier() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setFirstName("Петр");
        courierRequest.setPassword("12345");
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(11));
        return courierRequest;
    }
}

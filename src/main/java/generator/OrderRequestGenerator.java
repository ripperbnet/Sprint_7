package generator;

import dto.OrderRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class OrderRequestGenerator {

    public static OrderRequest getRandomOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        orderRequest.setLastName(RandomStringUtils.randomAlphabetic(5));
        orderRequest.setAddress("Moscow, Sovetskaya st. 55");
        orderRequest.setMetroStation("7");
        orderRequest.setPhone("+7 925 333 22 11");
        orderRequest.setRentTime("10");
        orderRequest.setDeliveryDate("2022-08-09");
        orderRequest.setComment("Test comment");
        return orderRequest;
    }
}

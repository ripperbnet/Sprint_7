import client.OrderClient;
import dto.OrderRequest;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static generator.OrderRequestGenerator.getRandomOrder;
import static org.apache.http.HttpStatus.SC_CREATED;

public class CreateNewOrderTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }


    @Test
    @DisplayName("Creating and order without color")
    @Description("Positive test of endpoint /api/v1/orders without color")
    public void createOrderWithoutColor() {
        OrderRequest randomOrder = getRandomOrder();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setColor(null);
        orderClient.create(randomOrder)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("track", Matchers.notNullValue());
    }
}
import client.OrderClient;
import dto.OrderRequest;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static generator.OrderRequestGenerator.getRandomOrder;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class GetOrderListTest {

    private OrderClient orderClient;

    private Integer track;


    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Getting order by track")
    @Description("Positive test of endpoint /v1/orders/track?t={track}")
    public void createOrderWithoutColor() {
        OrderRequest randomOrder = getRandomOrder();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setColor(null);
        track = orderClient.create(randomOrder)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("track", Matchers.notNullValue())
                .extract()
                .path("track");
        orderClient.getOrders(track)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("orders[0].id", Matchers.notNullValue());
    }
}


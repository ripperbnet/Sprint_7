import client.OrderClient;
import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.apache.http.HttpStatus.SC_OK;

public class GetOrderListTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Check of getting a list of orders")
    @Description("Positive test of api /api/v1/orders endpoint")
    public void getOrderList() {

        orderClient.getOrders()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("orders[0].id", Matchers.notNullValue());
        }
    }


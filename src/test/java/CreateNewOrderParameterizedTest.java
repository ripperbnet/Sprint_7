import client.OrderClient;
import dto.OrderRequest;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

import static generator.OrderRequestGenerator.getRandomOrder;
import static org.apache.http.HttpStatus.SC_CREATED;

@RunWith(Parameterized.class)
public class CreateNewOrderParameterizedTest {

    private OrderClient orderClient;

    private List<String> color;

    public CreateNewOrderParameterizedTest(List<String> color) {
        this.color = color;
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Parameterized.Parameters
    public static List<List<String>> color() {
        return List.of (
                List.of("BLACK"),
                List.of("GREY"),
                List.of("GREY", "BLACK")
        );
    }

    @Test
    @DisplayName("Parameterized test, creating order with different colors")
    @Description("Testing api endpoint /api/v1/orders with parameterized test")
    public void orderShouldBeCreated() {
        OrderRequest randomOrder = getRandomOrder();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setColor(color);
        orderClient.create(randomOrder)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("track", Matchers.notNullValue());
    }
}

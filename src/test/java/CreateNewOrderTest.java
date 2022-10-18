import client.OrderClient;
import com.beust.jcommander.Parameter;
import dto.OrderRequest;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static generator.OrderRequestGenerator.getRandomOrder;
import static org.apache.http.HttpStatus.SC_CREATED;

@RunWith(Parameterized.class)
public class CreateNewOrderTest {

    private OrderClient orderClient;

    private OrderRequest orderRequest;

    public String color = orderRequest.setColor(getColorData);



    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }


    public CreateNewOrderTest(List<String> color) {
        this.color = color;
    }



    @Parameterized.Parameters

    public static Object[][] getColorData() {
        return new Object[][] {
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"GREY", "BLACK"}}
        };
    }


    @Test
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
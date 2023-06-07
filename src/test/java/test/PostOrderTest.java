package test;

import conf.DataHelper;
import conf.EndpointManager;
import conf.dto.Order;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class PostOrderTest {
    @Test
    public void PostCorrectOrderTest() {
        Order order = new Order();
        order.setBookId(1);
        order.setCustomerName("Tester");

        given().auth().oauth2(DataHelper.token).body(order).contentType("application/json").when().post(EndpointManager.orders).then().statusCode(201);
    }
}

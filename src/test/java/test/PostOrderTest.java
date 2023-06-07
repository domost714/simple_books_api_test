package test;

import conf.DataHelper;
import conf.EndpointManager;
import conf.dto.Order;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;


public class PostOrderTest {
    @Test
    public void postCorrectOrderTest() {
        Order order = new Order();
        order.setBookId(1);
        order.setCustomerName("Tester");

        given().auth().oauth2(DataHelper.token).body(order).contentType("application/json")
                .post(EndpointManager.orders)
                .then().statusCode(201);
    }
    @Test
    public void verifyPerformanceWhenPostOrderTest() {
        Order order = new Order();
        order.setBookId(1);
        order.setCustomerName("Tester");

        given().auth().oauth2(DataHelper.token).body(order).contentType("application/json")
                .post(EndpointManager.orders)
                .then().time(lessThan(3L), TimeUnit.SECONDS);
    }
    @Test
    public void unauthorizedWhenPostOrderWithoutTokenTest() {
        Order order = new Order();
        order.setBookId(1);
        order.setCustomerName("Tester");

        given().body(order).contentType("application/json")
                .post(EndpointManager.orders)
                .then().statusCode(401);
    }
    @Test
    public void postOrderWithIncorrectIdTest() {
        Order order = new Order();
        order.setBookIdAsString(DataHelper.invalidId);
        order.setCustomerName("Tester");

        given().auth().oauth2(DataHelper.token).body(order).contentType("application/json")
                .post(EndpointManager.orders)
                .then().statusCode(400);
    }
}

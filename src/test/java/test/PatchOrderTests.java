package test;

import conf.DataHelper;
import conf.EndpointManager;
import conf.RequestHelper;
import conf.dto.Order;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatchOrderTests {
    @Test
    public void successfulPatchOrderTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");
        Order order = new Order();
        order.setBookId(null);
        order.setCustomerName("Change");

        given().auth().oauth2(DataHelper.token).body(order)
                .patch(EndpointManager.orders + id)
                .then().statusCode(204);

        given().auth().oauth2(DataHelper.token)
                .get(EndpointManager.orders + id)
                .then().statusCode(200); // TODO: Why response body is without customerName in REST ASSURED. In Postman works correctly
    }
    @Test
    public void verifyPerformanceWhenPatchOrderTest(){
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");
        Order order = new Order();
        order.setBookId(null);
        order.setCustomerName("Change");

        given().auth().oauth2(DataHelper.token).body(order)
                .patch(EndpointManager.orders + id)
                .then().statusCode(204).time(lessThan(3L), TimeUnit.SECONDS);
    }
    @Test
    public void unauthorizedWhenPatchOrderWithoutTokenTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");
        Order order = new Order();
        order.setBookId(null);
        order.setCustomerName("Change");

        given().body(order)
                .patch(EndpointManager.orders + id)
                .then().statusCode(401);
    }
    @Test
    public void unauthorizedWhenPatchOrderWithExpiredTokenTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");
        Order order = new Order();
        order.setBookId(null);
        order.setCustomerName("Change");

        given().auth().oauth2(DataHelper.expiredToken).body(order)
                .patch(EndpointManager.orders + id)
                .then().statusCode(401);
    }
    @Test
    public void unauthorizedWhenPatchOrderWithIncorrectIdTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");
        Order order = new Order();
        order.setBookId(null);
        order.setCustomerName("Change");

        given().auth().oauth2(DataHelper.token).body(order)
                .patch(EndpointManager.orders)
                .then().statusCode(404);
    }
    @Test
    public void notFoundWhenUserBTryToGetPatchPostedByUserATest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");
        Order order = new Order();
        order.setBookId(null);
        order.setCustomerName("Change");

        given().auth().oauth2(DataHelper.tokenB).body(order)
                .patch(EndpointManager.orders)
                .then().statusCode(404);
    }
}

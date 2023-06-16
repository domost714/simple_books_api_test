package test;

import conf.DataHelper;
import conf.EndpointManager;
import conf.RequestHelper;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PostOrderTests {
    @Test
    public void postCorrectOrderTest() {
        given().auth().oauth2(DataHelper.token).body(RequestHelper.postOrder(1, "Tester")).contentType("application/json")
                .post(EndpointManager.orders)
                .then().statusCode(201).body(containsString("true")).body("orderId", notNullValue());
    }
    @Test
    public void verifyPerformanceWhenPostOrderTest() {
        given().auth().oauth2(DataHelper.token).body(RequestHelper.postOrder(1, "Tester")).contentType("application/json")
                .post(EndpointManager.orders)
                .then().time(lessThan(3L), TimeUnit.SECONDS);
    }
    @Test
    public void unauthorizedWhenPostOrderWithoutTokenTest() {
        given().body(RequestHelper.postOrder(1, "Tester")).contentType("application/json")
                .post(EndpointManager.orders)
                .then().statusCode(401);
    }
    @Test
    public void unauthorizedWhenPostOrderWithExpiredTokenTest() {
        given().auth().oauth2(DataHelper.expiredToken).body(RequestHelper.postOrder(1, "Tester")).contentType("application/json")
                .post(EndpointManager.orders)
                .then().statusCode(401);
    }
    @Test
    public void unauthorizedWhenPostOrderWithIncorrectIdTest() {
        given().auth().oauth2(DataHelper.token).body(RequestHelper.postOrder(DataHelper.invalidId, "Tester")).contentType("application/json")
                .post(EndpointManager.orders)
                .then().statusCode(400);
    }
}

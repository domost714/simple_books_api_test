package test;

import conf.DataHelper;
import conf.EndpointManager;
import conf.RequestHelper;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class DeleteOrderTests {
    @Test
    public void noContentWhenGetDeletedOrderTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");

        given().auth().oauth2(DataHelper.token)
                .delete(EndpointManager.orders + id)
                .then().statusCode(204);

        given().auth().oauth2(DataHelper.token)
                .get(EndpointManager.orders + id)
                .then().statusCode(404).body(containsString("No order with id"));
    }
    @Test
    public void verifyPerformanceWhenDeleteOrderTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");

        given().auth().oauth2(DataHelper.token)
                .delete(EndpointManager.orders + id)
                .then().statusCode(204).time(lessThan(3L), TimeUnit.SECONDS);
    }
    @Test
    public void unauthorizedWhenDeleteOrderWithoutTokenTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");

        given()
                .delete(EndpointManager.orders + id)
                .then().statusCode(401);
    }
    @Test
    public void unauthorizedWhenDeleteOrderWithExpiredTokenTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");

        given().auth().oauth2(DataHelper.expiredToken)
                .delete(EndpointManager.orders + id)
                .then().statusCode(401);
    }

    @Test
    public void notFoundWhenUserBTryToGetOrderPostedByUserATest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");

        given().auth().oauth2(DataHelper.tokenB)
                .delete(EndpointManager.orders + id)
                .then().statusCode(404);
    }
    @Test
    public void notFoundWhenDeleteWithoutIdTest() {
        given().auth().oauth2(DataHelper.tokenB)
                .delete(EndpointManager.orders)
                .then().statusCode(404);
    }
}

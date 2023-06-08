package test;

import conf.DataHelper;
import conf.EndpointManager;
import conf.RequestHelper;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class GetOrderTest {
    @Test
    public void whenPostOrderThenGetItsDetailsByIDTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");

        given().auth().oauth2(DataHelper.token)
                .get(EndpointManager.orders + id)
                .then().statusCode(200).body(containsString("Testerski"));
    }
    @Test
    public void verifyPerformanceWhenPostOrderThenGetItsDetailsByIDTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");

        given().auth().oauth2(DataHelper.token)
                .get(EndpointManager.orders + id)
                .then().statusCode(200).time(lessThan(3L), TimeUnit.SECONDS);
    }
}

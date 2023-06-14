package test;

import conf.EndpointManager;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GetStatusTests {

    @Test
    public void okWhenGetStatusTest() {
        given()
                .get(EndpointManager.status)
                .then().statusCode(200);
    }
    @Test
    public void verifyPerformanceWhenGetStatusTest() {
        given()
                .get(EndpointManager.status)
                .then().time(lessThan(3L), TimeUnit.SECONDS);
    }
}

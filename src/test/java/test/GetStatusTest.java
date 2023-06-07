package test;

import conf.EndpointManager;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GetStatusTest {

    @Test
    public void okWhenGetStatusTest() {
        given().when().get(EndpointManager.status).then().statusCode(200);
    }
    @Test
    public void lessThanTwoSecondsResponseWhenGetStatusTest() {
        given().when().get(EndpointManager.status).then().time(lessThan(2L), TimeUnit.SECONDS);
    }
}

package test;

import conf.DataHelper;
import conf.EndpointManager;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GenerateTokenTests {
    @Test
    public void checkIfTokenIsValidTest() {
        given().body(DataHelper.generateTokenData).contentType("application/json")
                .post(EndpointManager.token)
                .then().statusCode(409);
        System.out.println("If test failed, you have to change token variable in DataHelper class according to API documentation");
    }
    @Test
    public void verifyPerformanceWhenGenerateTokenTest() {

        given()
                .body(DataHelper.generateTokenData).contentType("application/json")
                .post(EndpointManager.token)
                .then().time(lessThan(3L), TimeUnit.SECONDS);

    }
}

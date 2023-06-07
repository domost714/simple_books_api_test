package test;

import conf.EndpointManager;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostTokenTest {
    @Test
    public void checkIfTokenIsValid() {
        String token = "{\n" +
                "  \"clientName\": \"Test\",\n" +
                "  \"clientEmail\": \"dominik@tester.pl\"\n" +
                "}";

        given().body(token).contentType("application/json").when().post(EndpointManager.token).then().statusCode(409);
    }
}

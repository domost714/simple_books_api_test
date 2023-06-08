package test;

import conf.DataHelper;
import conf.EndpointManager;
import conf.RequestHelper;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class GetOrderTest {
    @Test
    public void whenPostOrderThenGetItsDetailsByIDTest() {
        String id = RequestHelper.postOrderAndGetItsId(1, "Testerski");

        given().auth().oauth2(DataHelper.token)
                .get(EndpointManager.orders + id)
                .then().statusCode(200).body(containsString("Testerski"));
    }
}

package test;

import conf.DataHelper;
import conf.EndpointManager;
import conf.RequestHelper;
import conf.dto.Order;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

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
}

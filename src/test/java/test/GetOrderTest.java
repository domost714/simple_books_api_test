package test;

import conf.DataHelper;
import conf.EndpointManager;
import conf.dto.Order;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class GetOrderTest {
    @Test
    public void whenPostOrderThenGetItsDetailsByIDTest() {
        Order order = new Order();
        order.setBookId(1);
        order.setCustomerName("Testerski");

        String id = given().auth().oauth2(DataHelper.token).body(order).contentType("application/json")
                .post(EndpointManager.orders)
                .then().statusCode(201).extract().path("orderId");

        given().auth().oauth2(DataHelper.token)
                .get(EndpointManager.orders + id)
                .then().statusCode(200).body(containsString("Testerski"));
    }
}

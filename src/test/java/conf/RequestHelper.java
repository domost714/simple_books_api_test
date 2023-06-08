package conf;

import conf.dto.Order;

import static io.restassured.RestAssured.given;

public class RequestHelper {
    public static String postOrderAndGetItsId(int bookId, String customerName) {
        Order order = new Order();
        order.setBookId(bookId);
        order.setCustomerName(customerName);

         String id = given().auth().oauth2(DataHelper.token).body(order).contentType("application/json")
                .post(EndpointManager.orders)
                .then().statusCode(201).extract().path("orderId");
         return id;
    }
}

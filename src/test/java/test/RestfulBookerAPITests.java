package test;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RestfulBookerAPITests extends BaseTest {

    @Test
    void authTest() {
        String body = "{ \"username\": \"admin\", " +
                "\"password\": \"password123\" }";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/auth")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("3060a39bd03d678"));
    }


    @Test
    void getBookingIds() {
        String body = "?firstname=admin&lastname=admin";
        given()
                .log().uri()
                .when()
                .get("/booking" + body)
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }


    @Test
    void getBooking() {

        given()
                .log().uri()
                .when()
                .get("booking/311")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void createBooking() {
        String body = "{\"firstname\": \"admin\", " +
                "\"lastname\": \"admin\"," +
                "\"totalprice\": 1455," +
                "\"depositpaid\": true," +
                "\"bookingdates\": " +
                "{\"checkin\":\"2018-01-01\"," +
                "\"checkout\": \"2019-01-01\" }," +
                "\"additionalneeds\": \"PlaySlow\"}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    //need cookie
    @Test
    void updateBooking() {

        String body = "{\"firstname\": \"admin\", " +
                "\"lastname\": \"admin\"," +
                "\"totalprice\": 1455," +
                "\"depositpaid\": true," +
                "\"bookingdates\": " +
                "{\"checkin\":\"2018-01-01\"," +
                "\"checkout\": \"2019-01-01\" }," +
                "\"additionalneeds\": \"KekW\"}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/booking/17174")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void partialUpdateBooking
            () {

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void deleteBooking() {
        given()
                .log().uri()
                .when()
                .delete("booking/311")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
}


package com.restfulbooker.test;

import com.restfulbooker.models.BookerModel;
import org.junit.jupiter.api.Test;

import static com.restfulbooker.specs.LoginSpec.loginRequestSpec;
import static com.restfulbooker.specs.LoginSpec.loginResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class RestfulBookerAPITests extends BaseTest {

    @Test
    void authTest() {
        String authToken = "e925d21d765101f";

        BookerModel body = new BookerModel();
        body.setUsername("admin");
        body.setPassword("password123");

        given()
                .spec(loginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract()
                .cookie(authToken);
    }



    @Test
    void getBookingIds() {
        String body = "";
        given()
                .log().uri()
                .when()
                .get("/booking?firstname=admin&lastname=admin")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("bookingid", notNullValue());
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
        BookerModel body = new BookerModel();
        body.setFirstname("admin");
        body.setLastname("admin");
        body.setTotalprice(1131);
        body.setDepositpaid(true);
        body.setAdditionalneeds("PlaySlow");
        body.setCheckin("2018-01-01");
        body.setCheckout("2019-01-01");
        body.setPassword("password123");

        String bodwy = "{\"firstname\": \"admin\", " +
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
                .cookie("token", "abc123")
                .when()
                .delete("booking/17174")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
}


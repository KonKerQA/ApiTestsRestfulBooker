package com.restfulbooker.test;

import com.restfulbooker.models.BookerModel;
import com.restfulbooker.models.BookingDatesModel;
import com.restfulbooker.models.LoginModelResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.restfulbooker.helper.RequestConverter.convertFileToString;
import static com.restfulbooker.specs.CreateBookingSpec.createBookingRequestSpec;
import static com.restfulbooker.specs.CreateBookingSpec.createBookingResponseSpec;
import static com.restfulbooker.specs.JsonSpec.jsonSpec;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.assertj.core.api.Assertions.assertThat;

public class RestfulBookerAPITests extends BaseTest {


    @Test
    public void authTest() throws IOException {
        String req = convertFileToString("request/auth.json");

        LoginModelResponse loginModelResponse = given()
                .config(config)
                .spec(jsonSpec)
                .body(req)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().as(LoginModelResponse.class);
    assertThat(loginModelResponse.getToken()).isNotNull();
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
        body.setBookingdates(BookingDatesModel.builder().checkin("2018-01-01").checkout("2019-01-01").build());
        body.setAdditionalneeds("PlaySlow");

        given()
                .spec(createBookingRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(createBookingResponseSpec);
    }


    @Test
    void getBookingIds() {
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

/*
    @Test
    void updateBooking() {
        given()
                .log().uri()
                .when()
                .get("restful-booker.herokuapp.com/booking/" + id)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("bookingid", notNullValue());
    }


    @Test
    void partialUpdateBooking() {
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
    void deleteBooking() {
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
*/


}


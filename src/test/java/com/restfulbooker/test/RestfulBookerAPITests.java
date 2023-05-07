package com.restfulbooker.test;

import com.restfulbooker.models.booking.BookerModel;
import com.restfulbooker.models.login.LoginModelResponse;
import com.restfulbooker.models.booking.NewBookerModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.restfulbooker.helper.RequestConverter.convertFileToString;
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

        BookerModel bookerModel = given()
                .config(config)
                .when()
                .get("booking/311")
                .then()
                .statusCode(200)
                .extract().as(BookerModel.class);

        assertThat(bookerModel.getFirstname()).isEqualTo("John");
        assertThat(bookerModel.getLastname()).isEqualTo("Smith");
        assertThat(bookerModel.getTotalprice()).isEqualTo(111);
        assertThat(bookerModel.isDepositpaid()).isEqualTo(true);
        assertThat(bookerModel.getBookingdates().getCheckin()).isEqualTo("2018-01-01");
        assertThat(bookerModel.getBookingdates().getCheckout()).isEqualTo("2019-01-01");
        assertThat(bookerModel.getAdditionalneeds()).isEqualTo("Breakfast");
    }

    @Test
    void createBooking() throws IOException {
        String req = convertFileToString("request/newBooking.json");

        NewBookerModel newbookerModel = given()
                .config(config)
                .spec(jsonSpec)
                .body(req)
                .when()
                .post("/booking")
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(NewBookerModel.class);

        assertThat(newbookerModel.getBookingid()).isNotNull();
        assertThat(newbookerModel.getBooking().getFirstname()).isEqualTo("Stellio");
        assertThat(newbookerModel.getBooking().getLastname()).isEqualTo("Costo");
        assertThat(newbookerModel.getBooking().getTotalprice()).isEqualTo(223);
        assertThat(newbookerModel.getBooking().isDepositpaid()).isEqualTo(true);
        assertThat(newbookerModel.getBooking().getBookingdates().getCheckin()).isEqualTo("2020-01-01");
        assertThat(newbookerModel.getBooking().getBookingdates().getCheckout()).isEqualTo("2021-01-01");
        assertThat(newbookerModel.getBooking().getAdditionalneeds()).isEqualTo("vodka");
    }


    @Test
    void getAllBookingIds() {
        NewBookerModel newbookerModel = given()
                .when()
                .get("/booking")
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(NewBookerModel.class);

        assertThat(newbookerModel.getBookingid()).isNotNull();
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


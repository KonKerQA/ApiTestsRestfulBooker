package com.restfulbooker.test;

import com.restfulbooker.models.BookerModel;
import com.restfulbooker.models.BookingDatesModel;
import com.restfulbooker.models.LoginModel;
import org.junit.jupiter.api.Test;

import static com.restfulbooker.specs.CreateBookingSpec.createBookingRequestSpec;
import static com.restfulbooker.specs.CreateBookingSpec.createBookingResponseSpec;
import static com.restfulbooker.specs.LoginSpec.loginRequestSpec;
import static com.restfulbooker.specs.LoginSpec.loginResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class RestfulBookerAPITests extends BaseTest {


    @Test
    public void authTest() {

        String authToken = "";
        LoginModel body = new LoginModel();
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
}


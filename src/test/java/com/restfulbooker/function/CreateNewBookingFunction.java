package com.restfulbooker.function;

import com.restfulbooker.models.booking.NewBookerModel;
import com.restfulbooker.models.login.LoginModelResponse;

import java.io.IOException;

import static com.restfulbooker.helper.RequestConverter.convertFileToString;
import static com.restfulbooker.specs.JsonSpec.jsonSpec;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class CreateNewBookingFunction {
    public static int createBooking() throws IOException {

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
                .extract().response().as(NewBookerModel.class);

        return newbookerModel.getBookingid();
    }
}

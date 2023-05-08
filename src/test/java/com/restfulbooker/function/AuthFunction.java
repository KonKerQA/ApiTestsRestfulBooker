package com.restfulbooker.function;

import com.restfulbooker.models.login.LoginModelResponse;

import java.io.IOException;

import static com.restfulbooker.helper.RequestConverter.convertFileToString;
import static com.restfulbooker.specs.JsonSpec.jsonSpec;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class AuthFunction {

    public static LoginModelResponse getAuthToken() throws IOException {
        String req = convertFileToString("request/auth.json");

        return given()
                .config(config)
                .spec(jsonSpec)
                .body(req)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().response().as(LoginModelResponse.class);
    }

}

package com.restfulbooker.test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

    }
}

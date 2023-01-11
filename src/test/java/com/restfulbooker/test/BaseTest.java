package com.restfulbooker.test;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "https://restful-booker.herokuapp.com";
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

    }
}

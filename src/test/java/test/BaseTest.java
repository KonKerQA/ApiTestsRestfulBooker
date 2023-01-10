package test;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://restful-booker.herokuapp.com";
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

    }
}

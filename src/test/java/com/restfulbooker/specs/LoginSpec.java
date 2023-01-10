package com.restfulbooker.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.restfulbooker.helper.APIListenerHelper.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static org.hamcrest.core.IsNull.notNullValue;

public class LoginSpec {
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri(baseUrl)
            .basePath("/auth")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("token", notNullValue())
            .build();
}
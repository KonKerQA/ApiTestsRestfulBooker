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

public class CreateBookingSpec {
    public static RequestSpecification createBookingRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri(baseUrl)
            .basePath("/booking")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification createBookingResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
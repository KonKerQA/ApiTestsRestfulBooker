package com.restfulbooker.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.restfulbooker.helper.APIListenerHelper.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class CreateBookingSpec {
    public static RequestSpecification createBookingRequestSpec = with()
            .filter(withCustomTemplates())
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
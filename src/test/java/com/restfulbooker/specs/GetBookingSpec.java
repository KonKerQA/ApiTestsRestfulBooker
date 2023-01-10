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

public class GetBookingSpec {
    public static RequestSpecification getBookingIdsRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri(baseUrl)
            .basePath("/booking")
            .param("firstname", "casanova")
            .param("lastname", "mas")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification getBookingIdsResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("bookingid", notNullValue())
            .build();
}
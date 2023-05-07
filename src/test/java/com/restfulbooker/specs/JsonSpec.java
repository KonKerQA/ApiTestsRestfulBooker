package com.restfulbooker.specs;

import io.restassured.specification.RequestSpecification;

import static com.restfulbooker.helper.APIListenerHelper.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class JsonSpec {
    public static RequestSpecification
            jsonSpec = with()
            .filter(withCustomTemplates())
            .accept("application/json")
            .contentType("application/json;charset=utf-8")
            .header("Accept-Charset", "utf-8");
}

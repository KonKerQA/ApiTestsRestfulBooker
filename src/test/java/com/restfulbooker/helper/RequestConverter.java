package com.restfulbooker.helper;

import io.restassured.internal.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class RequestConverter<T> {

    public static String convertFileToString(String requestName) throws IOException {
        InputStream is = RequestConverter.class.getClassLoader().getResourceAsStream(requestName);
        assert is != null;
        String request = new String(IOUtils.toByteArray(is));
        return request;
    }
}

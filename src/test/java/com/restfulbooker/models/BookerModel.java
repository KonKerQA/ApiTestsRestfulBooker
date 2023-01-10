package com.restfulbooker.models;

import lombok.Data;

import java.util.List;

@Data
public class BookerModel {
    private List<String> bookingdates;
    private int totalprice;
    private boolean depositpaid;
    private String username,
            password,
            firstname,
            lastname,
            additionalneeds,
            checkin,
            checkout;

}

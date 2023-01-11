package com.restfulbooker.models;

import lombok.Data;

@Data
public class BookerModel {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesModel bookingdates;
    private String additionalneeds;


}


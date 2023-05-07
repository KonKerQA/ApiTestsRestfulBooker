package com.restfulbooker.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookerModel {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesModel bookingdates;
    private String additionalneeds;

}


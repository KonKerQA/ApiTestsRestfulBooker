package com.restfulbooker.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDatesModel {
    private String checkin,
            checkout;
}

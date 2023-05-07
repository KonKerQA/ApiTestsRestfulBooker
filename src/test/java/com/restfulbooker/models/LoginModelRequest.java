package com.restfulbooker.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginModelRequest {
    private String username;
    private String password;
}

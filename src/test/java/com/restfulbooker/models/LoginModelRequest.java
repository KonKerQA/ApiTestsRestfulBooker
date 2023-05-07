package com.restfulbooker.models;

import lombok.Data;

@Data
public class LoginModelRequest {
    private String username;
    private String password;
}

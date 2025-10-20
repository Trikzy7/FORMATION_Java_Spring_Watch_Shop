package com.example.tp_cbtw_java_watch_shop.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String username;
    private String email;
    private String password;
    private String address;
}
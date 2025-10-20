package com.example.tp_cbtw_java_watch_shop.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email; // ou username
    private String password;
}
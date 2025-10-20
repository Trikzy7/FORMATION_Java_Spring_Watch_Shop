package com.example.tp_cbtw_java_watch_shop.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private Long userId;
    private String username;
    private String email;
}

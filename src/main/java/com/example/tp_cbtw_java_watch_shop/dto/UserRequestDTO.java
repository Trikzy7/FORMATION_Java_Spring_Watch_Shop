package com.example.tp_cbtw_java_watch_shop.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRequestDTO {
    private String username;
    private String password;
    private String email;
}

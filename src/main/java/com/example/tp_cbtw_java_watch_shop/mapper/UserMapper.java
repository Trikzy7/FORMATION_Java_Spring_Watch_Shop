package com.example.tp_cbtw_java_watch_shop.mapper;

import com.example.tp_cbtw_java_watch_shop.dto.UserRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.UserResponseDTO;
import com.example.tp_cbtw_java_watch_shop.model.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponseDTO toDto(User entity) {
        return UserResponseDTO.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .id(entity.getId())
                .build();

    }
}

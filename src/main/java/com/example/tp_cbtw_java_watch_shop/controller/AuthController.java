package com.example.tp_cbtw_java_watch_shop.controller;

import com.example.tp_cbtw_java_watch_shop.dto.LoginRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.LoginResponseDTO;
import com.example.tp_cbtw_java_watch_shop.dto.UserRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.UserResponseDTO;
import com.example.tp_cbtw_java_watch_shop.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO dto) {
        UserResponseDTO createdUser = authService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @PostMapping("/register/admin")
    public ResponseEntity<UserResponseDTO> registerAdminUser(@RequestBody UserRequestDTO dto) {
        UserResponseDTO createdUser = authService.registerAdminUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
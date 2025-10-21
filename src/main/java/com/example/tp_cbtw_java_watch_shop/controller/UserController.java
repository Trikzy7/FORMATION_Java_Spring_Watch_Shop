package com.example.tp_cbtw_java_watch_shop.controller;

import com.example.tp_cbtw_java_watch_shop.dto.UserRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.UserResponseDTO;
import com.example.tp_cbtw_java_watch_shop.dto.UserUpdateDTO;
import com.example.tp_cbtw_java_watch_shop.mapper.UserMapper;
import com.example.tp_cbtw_java_watch_shop.model.User;
import com.example.tp_cbtw_java_watch_shop.security.JwtService;
import com.example.tp_cbtw_java_watch_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Update user by ID
//    @PutMapping("/{id}")
//    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
//        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }

    // Delete user by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Récupérer son profil
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractEmail(token);

        User user = userService.findByEmail(email);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    // Mettre à jour son profil
    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateProfile(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UserUpdateDTO dto) {

        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractEmail(token);

        User user = userService.findByEmail(email);
        UserResponseDTO updated = userService.updateProfile(user.getId(), dto);

        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}

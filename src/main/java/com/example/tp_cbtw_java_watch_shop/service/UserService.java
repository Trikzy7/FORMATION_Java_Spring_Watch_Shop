package com.example.tp_cbtw_java_watch_shop.service;

import com.example.tp_cbtw_java_watch_shop.dto.UserRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.UserResponseDTO;
import com.example.tp_cbtw_java_watch_shop.mapper.UserMapper;
import com.example.tp_cbtw_java_watch_shop.model.User;
import com.example.tp_cbtw_java_watch_shop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        User user = UserMapper.toEntity(requestDTO);
        User saved = userRepository.save(user);
        return UserMapper.toDto(saved);
    }

    // Get all
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    // Get by ID
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return UserMapper.toDto(user);
    }

    // Update
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existing.setUsername(requestDTO.getUsername());
        existing.setEmail(requestDTO.getEmail());
        existing.setPassword(requestDTO.getPassword());

        User updated = userRepository.save(existing);
        return UserMapper.toDto(updated);
    }

    // Delete
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }

}

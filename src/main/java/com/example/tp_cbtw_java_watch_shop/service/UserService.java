package com.example.tp_cbtw_java_watch_shop.service;

import com.example.tp_cbtw_java_watch_shop.dto.UserRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.UserResponseDTO;
import com.example.tp_cbtw_java_watch_shop.dto.UserUpdateDTO;
import com.example.tp_cbtw_java_watch_shop.mapper.UserMapper;
import com.example.tp_cbtw_java_watch_shop.model.User;
import com.example.tp_cbtw_java_watch_shop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Create
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        User user = UserMapper.toEntity(requestDTO);
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setRole("USER");
        User saved = userRepository.save(user);
        return UserMapper.toDto(saved);
    }

    public UserResponseDTO createAdminUser(UserRequestDTO requestDTO) {
        User user = UserMapper.toEntity(requestDTO);
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setRole("ROLE_ADMIN");
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

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return user;
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


    // Mettre à jour le profil
    public UserResponseDTO updateProfile(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec cet ID"));

        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        if (dto.getPassword() != null) user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User updated = userRepository.save(user);
        return UserMapper.toDto(updated);
    }

    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec cet ID"));
    }

    // Méthode pour récupérer le rôle d'un utilisateur via son username
    public String getUserRoleByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return user.getRole();
    }

}

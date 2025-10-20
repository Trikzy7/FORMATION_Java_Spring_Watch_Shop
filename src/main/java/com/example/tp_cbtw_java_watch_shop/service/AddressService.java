package com.example.tp_cbtw_java_watch_shop.service;

import com.example.tp_cbtw_java_watch_shop.dto.AddressRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.AddressResponseDTO;
import com.example.tp_cbtw_java_watch_shop.dto.UserResponseDTO;
import com.example.tp_cbtw_java_watch_shop.mapper.AddressMapper;
import com.example.tp_cbtw_java_watch_shop.model.Address;
import com.example.tp_cbtw_java_watch_shop.model.User;
import com.example.tp_cbtw_java_watch_shop.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public AddressService(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    public AddressResponseDTO addAddress(Long userId, AddressRequestDTO dto) {
        UserResponseDTO user = userService.getUserById(userId);
        Address address = AddressMapper.toEntity(dto);
        address.setUser(userService.getUserEntityById(userId));
        Address saved = addressRepository.save(address);
        return AddressMapper.toDto(saved);
    }

    public List<AddressResponseDTO> getAddresses(Long userId) {
        User user = userService.getUserEntityById(userId);
        return addressRepository.findByUser(user).stream()
                .map(AddressMapper::toDto)
                .collect(Collectors.toList());
    }

    public AddressResponseDTO updateAddress(Long id, Long userId, AddressRequestDTO dto) {
        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adresse non trouvée"));

        // Vérifier que l'adresse appartient à l'utilisateur connecté
        if (!existing.getUser().getId().equals(userId)) {
            throw new RuntimeException("Vous n'êtes pas autorisé à modifier cette adresse");
        }

        AddressMapper.updateEntity(existing, dto);
        return AddressMapper.toDto(addressRepository.save(existing));
    }

    public void deleteAddress(Long id, Long userId) {
        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adresse non trouvée"));
        addressRepository.delete(existing);
    }
}
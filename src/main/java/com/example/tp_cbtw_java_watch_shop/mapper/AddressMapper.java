package com.example.tp_cbtw_java_watch_shop.mapper;

import com.example.tp_cbtw_java_watch_shop.dto.AddressRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.AddressResponseDTO;
import com.example.tp_cbtw_java_watch_shop.model.Address;

public class AddressMapper {

    public static Address toEntity(AddressRequestDTO dto) {
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        return address;
    }

    public static AddressResponseDTO toDto(Address entity) {
        AddressResponseDTO dto = new AddressResponseDTO();
        dto.setId(entity.getId());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setPostalCode(entity.getPostalCode());
        dto.setCountry(entity.getCountry());
        return dto;
    }

    public static void updateEntity(Address entity, AddressRequestDTO dto) {
        if (dto.getStreet() != null) entity.setStreet(dto.getStreet());
        if (dto.getCity() != null) entity.setCity(dto.getCity());
        if (dto.getPostalCode() != null) entity.setPostalCode(dto.getPostalCode());
        if (dto.getCountry() != null) entity.setCountry(dto.getCountry());
    }
}
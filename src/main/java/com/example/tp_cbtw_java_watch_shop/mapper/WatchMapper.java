package com.example.tp_cbtw_java_watch_shop.mapper;

import com.example.tp_cbtw_java_watch_shop.dto.WatchResponseDTO;
import com.example.tp_cbtw_java_watch_shop.model.Watch;
import com.example.tp_cbtw_java_watch_shop.dto.WatchRequestDTO;

public class WatchMapper {
    public static Watch toEntity(WatchRequestDTO dto) {
        Watch watch = new Watch();
        watch.setName(dto.getName());
        watch.setBrand(dto.getBrand());
        watch.setDescription(dto.getDescription());
        watch.setPrice(dto.getPrice());
        watch.setQuantity(dto.getQuantity());
        watch.setImageUrl(dto.getImageUrl());
        watch.setSpecificities(dto.getSpecificities());
        watch.setCategory(dto.getCategory());
        return watch;
    }

    public static WatchResponseDTO toDto(Watch entity) {
        WatchResponseDTO dto = new WatchResponseDTO();
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setImageUrl(entity.getImageUrl());
        dto.setSpecificities(entity.getSpecificities());
        dto.setCategory(entity.getCategory());
        return dto;
    }

}

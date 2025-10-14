package com.example.tp_cbtw_java_watch_shop.dto;

import com.example.tp_cbtw_java_watch_shop.model.Specificity;
import lombok.Data;

import java.util.List;

@Data
public class WatchResponseDTO {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private Double price;
    private int quantity;
    private String imageUrl;
    private List<Specificity> specificities;
    private String category;
}

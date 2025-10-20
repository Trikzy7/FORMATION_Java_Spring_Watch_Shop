package com.example.tp_cbtw_java_watch_shop.dto;

import com.example.tp_cbtw_java_watch_shop.model.Specificity;
import lombok.Data;

import java.util.List;

@Data
public class WatchRequestDTO {
    private String name;
    private String brand;
    private String description;
    private Double price;
    private String imageUrl;
    private List<Specificity> specificities;
    private String category;
}

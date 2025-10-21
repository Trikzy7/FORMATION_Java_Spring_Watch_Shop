package com.example.tp_cbtw_java_watch_shop.dto;

import com.example.tp_cbtw_java_watch_shop.model.Specificity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class WatchRequestDTO {

    @NotBlank(message = "Le nom est requis")
    private String name;

    @NotBlank(message = "La marque est requise")
    private String brand;

    @Size(max = 1000, message = "La description est trop longue")
    private String description;

    @NotNull(message = "Le prix est requis")
    @Min(value = 0, message = "Le prix doit être positif")
    private Double price;

    private String imageUrl;
    private List<Specificity> specificities;
    private String category;
}

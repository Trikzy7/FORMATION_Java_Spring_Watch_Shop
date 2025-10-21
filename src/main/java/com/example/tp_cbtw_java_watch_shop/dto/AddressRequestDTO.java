package com.example.tp_cbtw_java_watch_shop.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;


@Data
public class AddressRequestDTO {

    @NotBlank(message = "La rue est requise")
    private String street;

    @NotBlank(message = "La ville est requise")
    private String city;

    @NotBlank(message = "Le code postal est requis")
    private String postalCode;

    @NotBlank(message = "Le pays est requis")
    private String country;
}


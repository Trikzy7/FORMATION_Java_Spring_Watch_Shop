package com.example.tp_cbtw_java_watch_shop.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {
    private Long id;
    private String street;
    private String city;
    private String postalCode;
    private String country;
}

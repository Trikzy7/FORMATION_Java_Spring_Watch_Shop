package com.example.tp_cbtw_java_watch_shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "watch")
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String description;
    private Double price;
    private String imageUrl;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "watch_specificity",
            joinColumns = @JoinColumn(name = "watch_id"),
            inverseJoinColumns = @JoinColumn(name = "specificity_id")
    )
    @JsonBackReference
    private List<Specificity> specificities;

    private String category;
}



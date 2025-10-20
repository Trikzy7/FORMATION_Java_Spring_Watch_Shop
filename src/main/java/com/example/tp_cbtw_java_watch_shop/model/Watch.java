package com.example.tp_cbtw_java_watch_shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

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

    @ManyToMany
    @JoinTable(
            name = "watch_specificity",
            joinColumns = @JoinColumn(name = "watch_id"),
            inverseJoinColumns = @JoinColumn(name = "specificity_id")
    )
    @JsonBackReference
    private List<Specificity> specificities;

    private String category;
}



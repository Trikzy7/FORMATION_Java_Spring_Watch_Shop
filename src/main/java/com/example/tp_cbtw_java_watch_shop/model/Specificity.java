package com.example.tp_cbtw_java_watch_shop.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Data
@Entity
@Table(name = "specificity")
public class Specificity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToMany(mappedBy = "specificities")
    private List<Watch> watches;
}
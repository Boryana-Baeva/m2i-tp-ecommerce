package com.demo.api.business;

import jakarta.persistence.*;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String nom;

    public Integer getId() {
        return id;
    }
}

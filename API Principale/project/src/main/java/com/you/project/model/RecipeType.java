package com.you.project.model;

import jakarta.persistence.*;

@Entity
public class RecipeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String type;


    // Getters
    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}

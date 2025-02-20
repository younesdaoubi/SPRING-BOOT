package com.you.project.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

import java.util.List;

public class RecipeDTO {

    private Long id;

    @NotNull(message = "Le nom ne peut pas être null.")
    @Size(min = 2, max = 100, message = "La longueur du nom doit être comprise entre 2 et 100 caractères.")
    private String name;

    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères.")
    private String description;

    @NotNull(message = "Le typeId ne peut pas être null.")
    @Min(value = 1, message = "Le typeId doit être un nombre positif.")
    private Integer typeId;



    private List<Long> ingredientIds; // Liste des IDs d'ingrédients

    // Getters et Setters pour ingredientIds
    public List<Long> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<Long> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }



    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}

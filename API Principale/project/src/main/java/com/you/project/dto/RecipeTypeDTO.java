package com.you.project.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RecipeTypeDTO {

    private int id;

    @NotNull(message = "Le type ne peut pas être null.")
    @Size(min = 1, max = 50, message = "La longueur du type doit être comprise entre 1 et 50 caractères.")
    private String type;

    // Getters
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.you.project.controller;

import com.you.project.model.RecipeType;
import com.you.project.model.RecipeTypeEnum;
import com.you.project.repository.RecipeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe-types")
public class RecipeTypeController {

    @Autowired
    private RecipeTypeRepository recipeTypeRepository;

    // Ajouter un nouveau type de recette
    @PostMapping
    public RecipeType addRecipeType(@RequestBody String type) {
        RecipeType newType = new RecipeType();
        newType.setType(type);
        return recipeTypeRepository.save(newType);
    }
    // Obtenir tous les types de recettes
    @GetMapping
    public List<RecipeType> getAllRecipeTypes() {
        return recipeTypeRepository.findAll();
    }

    // Obtenir un type de recette par ID
    @GetMapping("/{id}")
    public RecipeType getRecipeType(@PathVariable Integer id) {
        return recipeTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecipeType not found with id: " + id));
    }

    // Mettre à jour un type de recette
    @PutMapping("/{id}")
    public RecipeType updateRecipeType(@PathVariable Integer id, @RequestBody String type) {
        RecipeType existingType = recipeTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecipeType not found with id: " + id));
        existingType.setType(type);
        return recipeTypeRepository.save(existingType);
    }

    // Supprimer un type de recette
    @DeleteMapping("/{id}")
    public void deleteRecipeType(@PathVariable Integer id) {
        recipeTypeRepository.deleteById(id);
    }
}


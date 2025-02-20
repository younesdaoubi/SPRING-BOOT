package com.you.project.service;

import com.you.project.dto.RecipeDTO;
import com.you.project.mapper.RecipeMapper;
import com.you.project.model.Recipe;
import com.you.project.model.RecipeType;
import com.you.project.repository.RecipeRepository;
import com.you.project.repository.RecipeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeService {

    // Déclaration des dépendances du service, injectées par Spring.
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final RecipeTypeRepository recipeTypeRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, RecipeMapper recipeMapper, RecipeTypeRepository recipeTypeRepository, RestTemplate restTemplate) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
        this.recipeTypeRepository = recipeTypeRepository;
        this.restTemplate = restTemplate;
    }

    // Méthode pour récupérer toutes les recettes, converties en DTO.
    public List<RecipeDTO> findAll() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::recipeToRecipeDTO)
                .collect(Collectors.toList());
    }

    public RecipeDTO save(RecipeDTO recipeDTO) {
        Recipe recipe = recipeMapper.recipeDTOToRecipe(recipeDTO);
        recipe.setType(getRecipeType(recipeDTO.getTypeId()));
        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeMapper.recipeToRecipeDTO(savedRecipe);
    }

    public RecipeDTO update(Long id, RecipeDTO recipeDTO) {
        Recipe existingRecipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));

        Recipe updatedRecipe = recipeMapper.recipeDTOToRecipe(recipeDTO);
        updatedRecipe.setId(id); // S'assurer que l'ID reste le même
        updatedRecipe.setType(getRecipeType(recipeDTO.getTypeId()));

        Recipe savedRecipe = recipeRepository.save(updatedRecipe);
        return recipeMapper.recipeToRecipeDTO(savedRecipe);
    }

    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }

    private RecipeType getRecipeType(Integer typeId) {
        return recipeTypeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid RecipeType ID: " + typeId));
    }

    public RecipeDTO findById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return recipeMapper.recipeToRecipeDTO(recipe.get());
        } else {
            throw new RuntimeException("Recipe not found with id: " + id);
        }
    }

    public RecipeType addRecipeType(String type) {
        RecipeType recipeType = new RecipeType();
        recipeType.setType(type);
        return recipeTypeRepository.save(recipeType);
    }

    public List<RecipeType> findAllRecipeTypes() {
        return recipeTypeRepository.findAll();
    }

    public RecipeType updateRecipeType(Integer id, String type) {
        RecipeType existingType = recipeTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecipeType not found with id: " + id));
        existingType.setType(type);
        return recipeTypeRepository.save(existingType);
    }

    //mtehode de supression
    public void deleteRecipeType(Integer id) {
        recipeTypeRepository.deleteById(id);
    }

    // Méthode pour obtenir un RecipeType par son ID.
    public RecipeType getRecipeTypeById(Integer id) {
        return recipeTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid RecipeType ID: " + id));
    }

}
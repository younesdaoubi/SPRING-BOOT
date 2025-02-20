package com.you.project.controller;

import com.you.project.dto.IngredientDTO;
import com.you.project.dto.RecipeDTO;
import com.you.project.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;


@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.ingredient.url}")
    private String ingredientServiceUrl;

    @Autowired
    public RecipeController(RecipeService recipeService, RestTemplate restTemplate) {
        this.recipeService = recipeService;
        this.restTemplate = restTemplate;
    }

    // Endpoint pour obtenir toutes les recettes
    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.findAll();
    }


    // Endpoint pour obtenir une recette par son ID
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
        try {
            RecipeDTO recipe = recipeService.findById(id);
            return ResponseEntity.ok(recipe);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour ajouter une nouvelle recette
    @PostMapping
    public RecipeDTO addRecipe(@Valid @RequestBody RecipeDTO recipeDTO) {
        return recipeService.save(recipeDTO);
    }

    // Endpoint pour mettre à jour une recette
    @PutMapping("/{id}")
    public RecipeDTO updateRecipe(@PathVariable Long id, @Valid @RequestBody RecipeDTO recipeDTO) {
        return recipeService.update(id, recipeDTO);
    }

    // Endpoint pour supprimer une recette
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.delete(id);
    }


    // Méthode pour récupérer un ingrédient par son ID
    @GetMapping("/ingredients/{ingredientId}")
    public ResponseEntity<IngredientDTO> getIngredientById(@PathVariable Long ingredientId) {
        String url = ingredientServiceUrl + "/" + ingredientId;
        try {
            IngredientDTO ingredient = restTemplate.getForObject(url, IngredientDTO.class);
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint pour get all ingredients
    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        String url = ingredientServiceUrl + "/all"; // Assuming the endpoint is /all
        try {
            // Le restTemplate appelle le service des ingrédients et récupère une liste d'ingrédients
            ResponseEntity<List<IngredientDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<IngredientDTO>>() {}
            );
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

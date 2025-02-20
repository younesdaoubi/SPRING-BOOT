package com.you.project.Api2.controller;

import com.you.project.Api2.dto.IngredientDTO;
import com.you.project.Api2.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/ingredients")
    public class IngredientController {
        @Autowired
        private IngredientService ingredientService;

        @GetMapping
        public List<IngredientDTO> getAllIngredients() {
            return ingredientService.findAll();
        }

        @GetMapping("/{id}")
        public IngredientDTO getIngredientById(@PathVariable Long id) {
            return ingredientService.getIngredientById(id);
        }
        /*@PostMapping
        public IngredientDTO addIngredient(@RequestBody IngredientDTO ingredientDTO) {
            return ingredientService.save(ingredientDTO);
        }*/
        @PostMapping
        public ResponseEntity<IngredientDTO> addIngredient(@RequestBody IngredientDTO ingredientDTO) {
            IngredientDTO savedIngredient = ingredientService.save(ingredientDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedIngredient);
        }


        @DeleteMapping("/{id}")
        public void deleteIngredient(@PathVariable Long id) {
            ingredientService.delete(id);
        }


    }






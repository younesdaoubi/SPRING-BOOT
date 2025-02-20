package com.you.project.Api2.service;

import com.you.project.Api2.dto.IngredientDTO;
import com.you.project.Api2.mapper.IngredientMapper;
import com.you.project.Api2.model.Ingredient;
import com.you.project.Api2.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    public List<IngredientDTO> findAll() {
        return ingredientRepository.findAll().stream()
                .map(ingredientMapper::ingredientToIngredientDTO)
                .collect(Collectors.toList());
    }
    //sauvegarde
    public IngredientDTO save(IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientMapper.ingredientDTOToIngredient(ingredientDTO);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return ingredientMapper.ingredientToIngredientDTO(savedIngredient);
    }
        //mise a jour
    public IngredientDTO update(Long id, IngredientDTO ingredientDTO) {
        Ingredient existingIngredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + id));
        ingredientMapper.updateIngredientFromDTO(ingredientDTO, existingIngredient);
        Ingredient updatedIngredient = ingredientRepository.save(existingIngredient);
        return ingredientMapper.ingredientToIngredientDTO(updatedIngredient);
    }
    // supression par id
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }
    //obtention par id
    public IngredientDTO getIngredientById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + id));
        return ingredientMapper.ingredientToIngredientDTO(ingredient);
    }
}

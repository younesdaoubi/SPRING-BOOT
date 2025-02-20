package com.you.project.Api2.mapper;

import com.you.project.Api2.dto.IngredientDTO;
import com.you.project.Api2.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    // Méthode pour convertir une entité Ingredient en DTO IngredientDTO.
    // Aucune configuration spécifique de mappage n'est requise ici,
    // MapStruct effectuera le mappage des champs automatiquement si les noms des champs correspondent.
    IngredientDTO ingredientToIngredientDTO(Ingredient ingredient);

    // Méthode inverse pour convertir un DTO IngredientDTO en entité Ingredient.
    // Semblable à la méthode précédente, MapStruct gère le mappage automatique des champs.
    Ingredient ingredientDTOToIngredient(IngredientDTO ingredientDTO);

    // Méthode pour mettre à jour une entité Ingredient existante à partir d'un DTO IngredientDTO.
    // @MappingTarget est utilisé pour indiquer que l'entité Ingredient fournie doit être mise à jour
    // plutôt que de créer une nouvelle instance.
    // Cette méthode est utile pour les opérations de mise à jour où vous avez déjà une entité
    // et vous souhaitez appliquer des changements à partir d'un DTO.
    void updateIngredientFromDTO(IngredientDTO ingredientDTO, @MappingTarget Ingredient ingredient);
}
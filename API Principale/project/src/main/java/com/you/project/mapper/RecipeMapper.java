package com.you.project.mapper;

import com.you.project.dto.RecipeDTO;
import com.you.project.model.Recipe;
import com.you.project.model.RecipeType;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    // Mapping d'une entité Recipe à un DTO RecipeDTO.
    // @Mapping définit comment les propriétés spécifiques sont mappées.
    // Ici, il extrait l'identifiant de type de la recette (typeId) de l'entité Recipe.
    @Mapping(target = "typeId", expression = "java(recipe.getType().getId())")
    RecipeDTO recipeToRecipeDTO(Recipe recipe);

    // Mapping d'un DTO RecipeDTO à une entité Recipe.
    // Il convertit l'identifiant de type de recette en un objet RecipeType.
    @Mapping(target = "type", expression = "java(typeIdToRecipeType(recipeDTO.getTypeId()))")
    Recipe recipeDTOToRecipe(RecipeDTO recipeDTO);

    // Méthode par défaut pour convertir un identifiant de type (Integer) en un objet RecipeType.
    // Utile pour le mapping inverse de DTO à entité.
    default RecipeType typeIdToRecipeType(Integer typeId) {
        if (typeId == null) {
            return null;
        }
        RecipeType recipeType = new RecipeType();
        recipeType.setId(typeId);
        return recipeType;
    }
}

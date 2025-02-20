package com.you.project.mapper;

import com.you.project.dto.RecipeTypeDTO;
import com.you.project.model.RecipeType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeTypeMapper {

    RecipeTypeDTO recipeTypeToRecipeTypeDTO(RecipeType recipeType);

    RecipeType recipeTypeDTOToRecipeType(RecipeTypeDTO recipeTypeDTO);

}

package com.you.project.repository;

import com.you.project.model.RecipeType;
import com.you.project.model.RecipeTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeTypeRepository extends JpaRepository<RecipeType, Integer> {
    Optional<RecipeType> findByType(RecipeTypeEnum type);
}

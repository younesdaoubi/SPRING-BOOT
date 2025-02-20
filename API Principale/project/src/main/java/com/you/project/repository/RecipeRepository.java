package com.you.project.repository;
import com.you.project.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByNameContaining(String name);
}


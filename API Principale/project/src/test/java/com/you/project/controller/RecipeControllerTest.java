package com.you.project.controller;

import com.you.project.dto.RecipeDTO;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getAllRecipesTest() {
        given()
                .when()
                .get("/recipes")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0)); // Assurez-vous d'avoir des recettes pour ce test
    }

    @Test
    public void addRecipeTest() {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setName("Test Recipe");
        recipeDTO.setDescription("Description for Test Recipe");
        recipeDTO.setTypeId(1); // Assurez-vous que ce typeId existe dans votre base de données de test

        given()
                .contentType(ContentType.JSON)
                .body(recipeDTO)
                .when()
                .post("/recipes")
                .then()
                .statusCode(201)
                .body("name", equalTo(recipeDTO.getName()))
                .body("description", equalTo(recipeDTO.getDescription()))
                .body("typeId", equalTo(recipeDTO.getTypeId()));
    }

    @Test
    public void updateRecipeTest() {
        long recipeId = 2; // ID d'une recette existante
        RecipeDTO updatedRecipe = new RecipeDTO(); // Remplir les champs mis à jour

        given()
                .contentType(ContentType.JSON)
                .body(updatedRecipe)
                .when()
                .put("/recipes/" + recipeId)
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteRecipeTest() {
        long recipeId = 1; // ID d'une recette existante

        when()
                .delete("/recipes/" + recipeId)
                .then()
                .statusCode(200);
    }



}

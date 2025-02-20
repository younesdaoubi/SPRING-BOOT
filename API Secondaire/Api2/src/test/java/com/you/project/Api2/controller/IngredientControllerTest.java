package com.you.project.Api2.controller;

import com.you.project.Api2.dto.IngredientDTO;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IngredientControllerTest {

    @Test
    public void getAllIngredientsTest() {
        given()
                .when()
                .get("/ingredients")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0)); // Assurez-vous d'avoir des ingrédients pour ce test
    }

    @Test
    public void addIngredientTest() {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName("Carotte");
        ingredientDTO.setDescription("Description de la carotte");

        given()
                .contentType(ContentType.JSON)
                .body(ingredientDTO)
                .when()
                .post("/ingredients")
                .then()
                .statusCode(201)
                .body("name", equalTo("Carotte"));
    }

    @Test
    public void deleteIngredientTest() {
        long ingredientId = 1; // ID d'un ingrédient existant

        given()
                .when()
                .delete("/ingredients/" + ingredientId)
                .then()
                .statusCode(200);
    }
}
package com.you.project.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeTypeControllerTest {


    @Test
    public void addRecipeTypeTest() {
        String type = "Nouveau type";

        given()
                .contentType(ContentType.TEXT)
                .body(type)
                .when()
                .post("/recipe-types")
                .then()
                .statusCode(200);
    }
}

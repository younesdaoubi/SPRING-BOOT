# SPRING-BOOT
primary and secondary apis


# Projet Java Spring Boot - Gestion des Recettes

## Description du Projet
Ce projet repose sur **Java Spring Boot** et se compose de **deux APIs distinctes** qui interagissent entre elles :

1. **API Principale : Gestion des Recettes**
2. **API Secondaire : Gestion des Ingrédients**

L'objectif est de fournir une solution complète pour la gestion des recettes de cuisine, en intégrant la gestion des ingrédients via une API dédiée.

---

## Technologies Utilisées
- **Spring Boot** (Framework principal)
- **Spring MVC** (Gestion des requêtes HTTP)
- **Spring Data JPA** (Interaction avec la base de données)
- **MySQL** (Base de données relationnelle)
- **RestTemplate** (Communication entre les APIs)
- **MapStruct** (Mappage des entités et DTOs)
- **Jakarta Validation** (Validation des entrées utilisateurs)
- **Postman & PhpMyAdmin** (Tests et gestion de la base de données)
- **Rest Assured** (Tests d'intégration)

---

## API Principale : Gestion des Recettes
Cette API permet la gestion complète des recettes de cuisine et communique avec l'API secondaire pour récupérer des informations sur les ingrédients.

### Fonctionnalités :
- **Gestion des Recettes** :
  - Listage des recettes (**GET** `/recipes`)
  - Ajout d'une nouvelle recette (**POST** `/recipes`)
  - Mise à jour d'une recette (**PUT** `/recipes/{id}`)
  - Suppression d'une recette (**DELETE** `/recipes/{id}`)
- **Communication avec l'API Secondaire** :
  - Consultation des ingrédients d’une recette via **RestTemplate**
  - Endpoint : `/recipes/ingredients/{ingredientId}`
- **Mappage des Entités** avec **MapStruct**
- **Validation des Données** avec **Jakarta Validation**
- **Base de Données** : **MySQL** sur **PORT 8080**

---

## API Secondaire : Gestion des Ingrédients
Cette API permet de gérer les ingrédients indépendamment des recettes.

### Fonctionnalités :
- **Gestion des Ingrédients** :
  - Listage des ingrédients (**GET** `/ingredients`)
  - Ajout d’un ingrédient (**POST** `/ingredients`)
  - Suppression d’un ingrédient (**DELETE** `/ingredients/{id}`)
- **Mappage des Entités** avec **MapStruct**
- **Base de Données** : **MySQL** (paramètres similaires à l’API principale) sur **PORT 8082**

---

## Communication entre les APIs
L'**API principale** utilise **RestTemplate** pour interroger l’API secondaire sur les ingrédients.
L'endpoint `/recipes/ingredients/{ingredientId}` permet d’accéder aux détails d’un ingrédient spécifique.

Les deux APIs utilisent **la même base de données MySQL**, ce qui assure la cohérence des données.

---

## Tests & Validation
- **Tests d’intégration** réalisés avec **Rest Assured** pour les deux APIs séparément.
- Vérification des endpoints via **Postman** et **PhpMyAdmin**.

---

## Conclusion
Ce projet démontre une **architecture modulaire et évolutive**, avec une séparation claire entre la gestion des recettes et des ingrédients. Grâce à **Spring Boot**, **RestTemplate** et **MySQL**, il offre une solution robuste pour la gestion de contenus culinaires.

Ce projet peut être étendu avec des fonctionnalités supplémentaires comme l’authentification des utilisateurs, la gestion des catégories de recettes ou encore l’intégration avec un front-end dédié.

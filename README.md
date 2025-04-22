# MOVIE REVIEW WEB APPLICATION 🎥 

<picture>
 <source media="(prefers-color-scheme: dark)" srcset="https://yt3.googleusercontent.com/fIUvbz0dArTYtcHqe-kn2fFTGJhIFcxo6q1MaqpDOQVd8dPXQjmrb96D1mqwzGdjW--PbaPkDmk=s900-c-k-c0x00ffffff-no-rj">
 <source media="(prefers-color-scheme: light)" srcset="https://yt3.googleusercontent.com/fIUvbz0dArTYtcHqe-kn2fFTGJhIFcxo6q1MaqpDOQVd8dPXQjmrb96D1mqwzGdjW--PbaPkDmk=s900-c-k-c0x00ffffff-no-rj">
 <img alt="YOUR-ALT-TEXT" src="https://yt3.googleusercontent.com/fIUvbz0dArTYtcHqe-kn2fFTGJhIFcxo6q1MaqpDOQVd8dPXQjmrb96D1mqwzGdjW--PbaPkDmk=s900-c-k-c0x00ffffff-no-rj">
</picture>

## 📌 Table of Contents
- [Authors](#authors)
- [Description](#description)
- [Entities Used](#entities-we-used)
- [Requested Features](#requested-features)
- [Navigation 🧭](#navigation-)
- [Execution Instructions](#execution-instructions)
- [Entities Diagram 📈](#entities-diagram-)
- [Class Diagram & Templates 📈](#class-diagram--templates-)
- [Team Contributions 👥](#members-participation-)
  - [Zaira](#zaira)
  - [Manuel](#manuel)
  - [Miguel](#miguel)
  - [Carlos](#carlos)



## Authors:
### Carlos Ivorra Salinas
### Manuel Torres Juárez
### Zaira Ruiz Fernández
### Miguel
| Name | Email | Github |
|-----:|-----------|-----------|
| Carlos Ivorra Salinas | c.ivorra.2021@alumnos.urjc.es | carlosivorrasalinas |
| Manuel | mm.torres.2022@alumnos.urjc.es | tempusfugit04 |
| Zaira Ruiz Fernández | z.ruiz.2022@alumnos.urjc.es | ZairaURJC |
| Miguel Lameiro Martinez | m.lameiro.2023@alumnos.urjc.es | The-lost-Apolo |

## Description:
### This is a web application that we developed for "Distribuited systems" in our collegue degree. We had to develop a web application using _Spring_, _MySQL_ and _GitHub_.
### We decided a movie rating application because movies was a common interest between ourselves.


## Entities we used


| Entity | Description |
|-----:|-----------|
|     User| 3 types: Admin, anonymous and registered. This entitie define all the types of users. In this first part, however, there is only one user that can do everything. One user can have many reviews.|
|     Movie| The movies that are available for the users to review or see their info on the application. One movie can have many actors and reviews.   |
|     Cast| The actors that are available for the users to see their info on the application. One actor can have many movies.      |
|     Review| Users reviews of a movie. One review has one movie and one user associated.      |

# REQUESTED FEATURES
>Below there are all requests made in the project description  

  -Java version: 21  
  -MySQL version: 8.0.33  
  -Maven version: 4.0.0  
  -Spring Boot version: 3.4.2  
  -VisualStudioCode + SpringBoot  

## NAVIGATION 🧭
>Screenshots of the main pages in the application and navigation diagram  

   **·HOME PAGE (MOVIES LIST)**  
![home](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/home_template.png)
A banner will always appear at the top of the screen, showing where we are so we never can be confused.
This time, we are in "Página de inicio".
The three buttons of the header lead to this, the cast list or the users reviews, in that order.  
The title of each movie leads to its info page.  
The "Iniciar sesión" button leads to the login form.
The "Volver a la página de inicio" link always leads to this page, wathever page are you on.
The logo icon will lead you to the home page as the "Volver a la página de inicio" button.

   **·CAST LIST**  
![castList](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/castList_template.png)  
The three buttons of the header are the same that in the home page and their actions are the same, too.  
The name of each actor leads to its info page. 

   **·USER REVIEWS**  
![my_reviews](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/my_reviews_error_template.png)  
This time, we'll get an error due to not being logged in and thus, not having the permission to access to our reviews (we don't exist to the web yet).
The banner show we are in a current state of error with "Página de error"
The web tell us to try to log in, but first we'll look the movie and cast templates.
We can use either the logo or the bottom link to go back to home. 

   **·MOVIE INFO**  
![movie](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/movie_template1.png)
The banner show us where we are, this time in "Mostrando datos seleccionados". 
We can see the title of the movie "Mortadelo y filemón contra Jimmy el Cachondo" with its poster image.
Below of the poster, it appears the movie info in a coloured section. Such as "Año de lanzamiento, sinopsis..."
![movie](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/movie_template2.png)
The name of each actor in "Reparto" section leads to its info page.  
The trailer link open a new window with the trailer video (if the movie has one).
![movie](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/movie_template3.png)
At the bottom, you can see all the reviews users have made. Each review has the username's name, title and the review itself. 

   **·CAST INFO**  
![cast](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/cast_template1.png)  
As the movie template, you can see the name of the actor with its image, below more data about the actor.
![cast](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/cast_template2.png) 
The title of each movie in "Películas" section leads to its info page.  

   **·LOGIN FORM**  
![login](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/login_template.png) 
Now that we finished the tour for those who haven't log in yet, let's see what happens if we do it.
There are 2 fields to complete, username and password. If we log as a normal registered user, something will happen... keep reading to discover it!

   **·REGISTERED HOME PAGE**
![login](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/registered_home_template.png) 
Once registered, we will be back to the home page. You can see the new "Mi perfil" and "Cerrar sesión" buttons.
We couldn't see this before, but there is a "Más resultados" button which shows more entities (movies, cast, reviews). The cast list doesn't change from this template, so let's go straight up to "Reseñas".

   **·REGISTERED USER REVIEWS**
![user_reviews](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/registered_my_reviews_template.png) 
Now that we are registered, we can actually see all our reviews. 
There is a "Borrar reseña" button to delete the selected review quickly without having to search it on the movies.

   **·MY PROFILE**
![profile](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/my_profile_template.png)
Here in our profile we can see our data like "Nombre de usuario" or "Rol". Things like the password are not showed to more security.
We can select some buttons like "Cerrar sesión" (log out) "Mis reseñas" (my_reviews_template) "Modificar datos" (to change our username or password) or "Borrar mi usuario" (to delete our user from the web database)

   **·REGISTERED MOVIE INFO**
![movie](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/movie_template4.png)
Now in the movie you can see that the reviews have two more buttons, "Borrar reseña" and "Añadir reseña".

   **·REVIEW FORM**  
![new_review](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/new_review_template.png)  
The "Enviar" button leads to the review created page.
Now let's change this, if you log as admin you will unlock even more functionality.  

   **·ADMIN HOME PAGE**
![new_review](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/admin_home_template.png)
Once you have logged in as an ADMIN. In a movie you can see two new buttons "Modificar película" and "Borrar película". And in the home page you will have a new button "Añadir película". Either "Modificar" o "Añadir" will lead you to the movie form.

   **·MOVIE FORM**  
![new_or_modify_movie](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/new_or_modify_movie_template.png)  
You can fill the data fields of the movie.
The "Guardar película" button leads to the movie created (or modified) page.

   **·CAST FORM**  
![new_or_modify_cast](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/new_or_modify_cast_template.png)  
The "Guardar actor" button leads to the cast created (or modified) page.  
This form has default info in case of being for modification purposes (the cast info).  

   **·ENTITY CREATED, DELETED OR MODIFIED**  
![movie_created](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/movie_created_template.png)  
This is the movie created page, but there are also similar pages for: review created, cast created, user created, cast modified, movie modified, user modified, movie deleted, cast deleted, review deleted and user deleted.  
Their button leads to the movies list, cast list, user reviews or home page (movies list by default) depending on the entity.  

   **·GENERAL ERROR**  
![error](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/error_template.png)  
This page appears when a generic error has occurred. It allows you to return to the home page. There are more variations (access error, movie not found, deleting a review that you don't owe it...)  

   **·MOVIE/CAST NOT FOUND ERROR**  
![movieNotFound](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/movieNotFound_template.png)  
This error page appears when the user is trying to access a non-existent movie or cast (for cast the page is a little different). It allows you to return to the home page.  

   **·ACCESS ERROR**  
![access_error](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/access_error_template.png)
If you don't have the needed role to access to certain functionality, an error will appear.

   **·OWNING ERROR**
![owning_error](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/owning_error_template.png)
If you try to delete another review that you dont own it, an error will appear.

   **·NAVIGATION DIAGRAM**  
![navigation_diagram](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/navigation_diagram.png)  
You can go to general error page from all other pages (if any error occurs).  
All forms lead you to the entity created/modified page when finished and all delete buttons lead you to the entity deleted page.  
All the "volver a la pgina de inicio" links lead you to the home page.  

## EXECUTION INSTRUCTIONS
It is needed the following features:
1. Java 21 [Download here](https://www.oracle.com/es/java/technologies/downloads/#java21)
2. MySQL 8.0.33 [Download here](https://dev.mysql.com/downloads/workbench/)
3. Maven 3.9.9 (Although it does exist the 4.0.0 version, it is recommended to download the 3.9.9 version)
[Download here](https://maven.apache.org/download.cgi)
4. Spring Boot 3.4.2 
5. Visual Studio Code + SpringBoot 

Then, follow this steps:
1. Download this repository with ZIP and unzip it
2. Download MySQL workBench
3. Type user: root password: URJC2019!
4. Create a new scheme called "moviesapp"
5. Run the Application.java in VS
6. Go to https://localhost:8443


## ENTITIES DIAGRAM 📈

![entitiesDiagram](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/entitiesDiagram.png)

## CLASS DIAGRAM AND TEMPLATES 📈

![classesDiagram](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/classesDiagram.png)



# MEMBERS PARTICIPATION 👥
>Description of every member participation on the project


## ZAIRA
## DESCRIPTION OF THE TASKS DONE ✍️

I added web and REST security and everything related to users (user profile, functionality to create, modify, delete and view users and login and creation forms), created the postman collection and OpenApi documentation, participated in the creation of REST controllers and corrected some errors in several files.  

## 5 most relevant commits ⬆️
| Rank | Commit |
|-----:|-----------|
|     1| [Added keystore, security configuration without conection to bbdd yet, RepositoryUserDetailsService and some corrections in User, UserRepository and ReviewController for the correct operation](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/fe3a3c44a535687a035a1e9632b4cfc7d52be709)|
|     2| [Add user and modify user functions](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/a4cda5fdf3eeb33ad8858227270899f269bdcca6) |
|     3| [Add rest security first part](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/e27b77af7c55b0f80319fe729cf587e2e60b57a9#diff-2dd28e7ef430e1ee0350319324e59749efe60b2c63e8ca2037ac99399234ef90) |
|     4| [Add rest security second part](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/1f1f24b42545c373a6b2c36f92147dbdc90819bc)|
|     5| [Correct ReviewRestController, ReviewService, ReviewDTO, ReviewBasicDTO, other DTOs and some other things for the correct functioning of the web and rest part of the program](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/1b3577e7130a493a2055306c0f6c9f5e9d69f0bf)       |

## 5 files in wich I have participated the most 📝
| Rank | [File link] |
|-----:|-----------|
|     1| [LoginController.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blame/main/practica_grupo17/src/main/java/es/codeurjc/web/controller/web/LoginController.java)|
|     2| [UserController.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blame/main/practica_grupo17/src/main/java/es/codeurjc/web/controller/web/UserController.java) |
|     3| [Postman test.postman_collection.json](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blame/main/Postman%20test.postman_collection.json) |
|     4| [SecurityConfig.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blame/main/practica_grupo17/src/main/java/es/codeurjc/web/security/SecurityConfig.java)|
|     5| [LoginRestController.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blame/main/practica_grupo17/src/main/java/es/codeurjc/web/controller/rest/LoginRestController.java)       |



## MANUEL
## DESCRIPTION OF THE TASKS DONE ✍️
I added a lot of styles and created some templates, updated the sampleData, made diagrams in README, added functionality of DTO to MoviesController, CastController, ReviewController, MoviesService, CastService, UserService and ReviewService (including some mappers and DTOs), added some of the API REST controllers like MovieRestController and CastRestController, and fixed some bugs or errors in various files.

## 5 most relevant commits ⬆️
| Rank | Commit |
|-----:|-----------|
|     1| [Added multiple styles to templates.](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/4246f55a175a745d875155fe119d055cd3a9e683)|
|     2| [More adjusments and changes to services, mappers, web and rest controllers to add the API REST functionality. Still in progress.](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/d358d6490accb537fa3ad842a766d7007e05b2bd)|
|     3| [Added the review right functionality to movies and users. Modified userService and userRestController to update to DTOs, added SampleData complete. Added new access error template to 403 errors. Added the initial samples to the web.](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/417826247cdb7b0cbec152f0bfc37874995a7535)|
|     4| [Changes to services, controllers, mappers, DTOs and databaseInitializer to improve the functionality. Its pending to fix it in the future, its not definitive](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/c2e271f3a062e3ff1f448acbd055086d00224112)|
|     5| [Catalogue and form style changes added with css](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/ad9754d49055f97459f12d9a054abaad98643fa9)|

# 5 files in wich I have participated the most 📝
| Rank | File link |
|-----:|-----------|
|     1| [MoviesController.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/controller/web/MoviesController.java)|
|     2| [CastService.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/services/CastService.java)|
|     3| [styles.css](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/resources/static/styles.css)|
|     4| [MoviesService.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/services/MoviesService.java)|
|     5| [MovieRestController.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/controller/rest/MovieRestController.java)|


## MIGUEL
## DESCRIPTION OF THE TASKS DONE ✍️
I contributed to the IMDb-style web project by implementing dynamic pagination for movies, reviews, and cast, and enhancing the frontend with JavaScript logic to load content. I also worked on DTO and mapper creation for entities such as Cast and User, helping to streamline data transfer and service logic. Additional improvements include the setup of default images for missing resources.

## 5 most relevant commits ⬆️
| Rank | Commit |
|-----:|-----------|
|     1| [Adding pagination to Movies, Reviews, and Cast entities](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/1c59d27784dae947a231adbf4939efcb7f1a8a0f) |
|     2| [Adding User Mapper and refactorazing the User and Cast services](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/d11c233f3c3e448931d3469d3255441f2067bb55) |
|     3| [Adding cast mapper](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/7f15d3f378a93a5f0efd4e5ce0fde64f2e8212a7) |
|     4| [Adding default images feature when modifying cast and movies](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/43b2cf56af6f009e51a1d7ca0ce59c5269a45c24) |
|     5| [Adding entities DTOs](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/f8f30449196bb0be0a56e4f968fdfec66a8eb188) |

## 5 files in wich I have participated the most 📝
| Rank | File link |
|-----:|-----------|
|     1| [CastMapper.java](practica_grupo17/src/main/java/es/codeurjc/web/mapper/CastMapper.java) |
|     2| [loadCast.js](practica_grupo17/src/main/resources/static/loadCast.js) |
|     3| [CreateCastDTO.java](practica_grupo17/src/main/java/es/codeurjc/web/dto/cast/CreateCastDTO.java) |
|     4| [loadReviews.js](practica_grupo17/src/main/resources/static/loadReviews.js) |
|     5| [MovieDTO.java](practica_grupo17/src/main/java/es/codeurjc/web/dto/movie/MovieDTO.java) | 

## CARLOS
# DESCRIPTION OF THE TASKS DONE ✍️
Added multiple changes to the sample data and improved styles.

## 5 most relevant commits ⬆️
| Rank | Commit |
|-----:|-----------|
|     1| [Added new movies, cast and reviews to the sample data.](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/40d73965fe4d70dd6943addf1671a8a621ff5476)|
|     2| [Changed the css styles completing changes in buttons, titles and plain text. Also adding margins to the main page in order to have a more professional overall look.](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/a7fe3699f09e001020545f7ef7c43be56247eef7)|
|     3| [Added styles to the profile template](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/923ff88813a83ba9c550818c38c67138632b2a01)|
|     4| [Added animations to main page](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/6b6f0e3cf04a99a540899826cc5c2a789f032a6b)|
|     5| [Added margins to the rest of the page](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/548e9bbd8285da10116260a53eb62cd1bf4bc560)|

## 5 files in wich I have participated the most 📝
| Rank | File link |
|-----:|-----------|
|     1| [SampleDataService.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/services/SampleDataService.java)|
|     2| [styles.css](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/resources/static/styles.css) |
|     3| [my_profile_template.html](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/resources/templates/my_profile_template.html) |
|     4| [home_template.html](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/resources/templates/home_template.html)|
|     5| [banner_home_template.html](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/resources/templates/banner_home_template.html)|








[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/D1C1HU9V)

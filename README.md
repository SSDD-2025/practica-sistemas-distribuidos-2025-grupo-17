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
| Carlos Ivorra Salinas |  | carlosivorrasalinas |
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
The three buttons of the header lead to this, the cast list or the users reviews, in that order.  
The title of each movie leads to its info page.  
The "añadir película" button leads to the movie form.  
The "volver a la página de inicio" link always leads to this page, wathever page are you on.  

   **·CAST LIST**  
![castList](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/castList_template.png)  
The three buttons of the header are the same that in the home page and their actions are the same, too.  
The name of each actor leads to its info page.  
The "añadir actor" button leads to the cast form.  

   **·USER REVIEWS**  
![my_reviews](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/my_reviews_template.png)  
This time the header is the same, too.  
All "borrar reseña" buttons do the same: delete the review associated and return the review deleted page.  

   **·MOVIE INFO**  
![movie](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/movie_template.png)  
The name of each actor in "Reparto" section leads to its info page.  
The trailer link open a new window with the trailer video (if the movie has one).  
The "borrar película" button deletes the movie and returns the movie deleted page.  
The "modificar película" button leads to the movie form.  
The "añadir reseña" button leads to the review form.  
The "borrar reseña" buttons do the same that in the last page (user reviews).  

   **·CAST INFO**  
![cast](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/cast_template.png)  
The title of each movie in "Películas" section leads to its info page.  
The "borrar cast" button deletes the cast and returns the cast deleted page.  
The "modificar cast" button leads to the cast form.  

   **·MOVIE FORM**  
![new_or_modify_movie](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/new_or_modify_movie_template.png)  
The "enviar consulta" button leads to the movie created (or modified) page.  
This form has default info in case of being for modification purposes (the movie info).  

   **·CAST FORM**  
![new_or_modify_cast](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/new_cast_template.png)  
The "enviar consulta" button leads to the cast created (or modified) page.  
This form has default info in case of being for modification purposes (the cast info).  

   **·REVIEW FORM**  
![new_review](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/new_review_template.png)  
The "enviar consulta" button leads to the review created page.  

   **·ENTITY CREATED, DELETED OR MODIFIED**  
![review_created](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/review_created_template.png)  
This is the review created page, but there are also similar pages for: movie created, cast created, movie modified, cast modified, movie deleted, cast deleted and review deleted.  
Their button leads to the movies list, cast list or user reviews depending on the entity.  

   **·GENERAL ERROR**  
![error](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/error_template.png)  
This page appears when an error has occurred (except when the error is movie or cast not found). It allows you to return to the home page.  

   **·MOVIE/CAST NOT FOUND ERROR**  
![movieNotFound](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/movieNotFound_template.png)  
This error page appears when the user is trying to access a non-existent movie or cast (for cast the page is a little different). It allows you to return to the home page.  

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
6. Go to https://localhost/3306


## ENTITIES DIAGRAM 📈

![entitiesDiagram](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/entitiesDiagram.png)

## CLASS DIAGRAM AND TEMPLATES 📈

![classesDiagram](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/readme_images/classesDiagram.png)



# MEMBERS PARTICIPATION 👥
>Description of every member participation on the project


## ZAIRA
## DESCRIPTION OF THE TASKS DONE ✍️

Altough I have participated in some templates and other things, my main tasks were the creation of the controllers and services, the database integration and the inclusion of entity, navigation and requirements information in the readme file.  

## 5 most relevant commits ⬆️
| Rank | Commit |
|-----:|-----------|
|     1| [Database funcionality finished (N:M relation between movies and cast and 1:M relation between users and reviews (new repositories and changes in controllers, services and entities), my_reviews_template creation)](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/6f857507db107c31459a4a327094833a59965265)|
|     2| [New header with show cast list, show movie list and show my reviews functions and some upgrades in modify, create and delete functions](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/a1f0368fd0f94be11c71f8fd4ccfa1132c51af1d) |
|     3| [Images in database and some structure changes](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/261e68b7ce2bb6555ac37628290c6b3fb71c777f) |
|     4| [Some little corrections for modifyMovie, modifyCast and newReview functions (and their html) and new my_reviews_template](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/baa3ea11a123cecb557e5a8a10f43465ffecfb36)|
|     5| [New entity, controller and service for users and some changes in the controllers structure](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/33faed76bffb53ab9d10c9312632d61c2331fdd9)       |

## 5 files in wich I have participated the most 📝
| Rank | [File link] |
|-----:|-----------|
|     1| [CastService.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/services/CastService.java)|
|     2| [MoviesService.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/services/MoviesService.java) |
|     3| [Cast.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/entities/Cast.java) |
|     4| [ReviewController.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/controller/ReviewController.java)|
|     5| [MoviesController.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/controller/MoviesController.java)       |



## MANUEL
## DESCRIPTION OF THE TASKS DONE ✍️
I added most styles in templates, created most templates, created the sampleData, made diagrams in README, added functionality of dataBase to CastController, MoviesConstroller (and functions related), and fixing some bugs or errors in various files.

## 5 most relevant commits ⬆️
| Rank | Commit |
|-----:|-----------|
|     1| [2nd part to include CSS style to all templates and fixing some minor errors in some .java classes](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/87e64d08d1a90786d92a9c9a9aea5ab8bdf2f443)|
|     2| [Changes to implement DataBase, new repository classes, and changes in entity relations](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/a4ffe6acdabb9f419cee705f11c9d83a9e1774d2)|
|     3| [Cast and Movie fixes and changes to import DataBase functionality](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/2c71c8d892bc657e662907e6b13cd8af7134446c)|
|     4| [Fixes inconsistencies when modify movies and cast, fixed minor error in sampledata castBirthDate format, added new styles to reviews, fixed bug in delete review on a movie template.](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/f592e1082d9cffa85fce4473bcd213334ae62ffc)|
|     5| [Added new templates to add new entities and modified old ones](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/bd66b1374a0467641a66baae6306c1442376589c)|

# 5 files in wich I have participated the most 📝
| Rank | File link |
|-----:|-----------|
|     1| [SampleDataService.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/services/SampleDataService.java)|
|     2| [MoviesController.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/controller/MoviesController.java)|
|     3| [CastController.java](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/java/es/codeurjc/web/controller/CastController.java)|
|     4| [home_template.html](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/resources/templates/home_template.html)|
|     5| [styles.css](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/blob/main/practica_grupo17/src/main/resources/static/styles.css)|


## MIGUEL
## DESCRIPTION OF THE TASKS DONE ✍️
I improved the IMDb-style web project by enhancing error handling for missing movies and cast, refining the user interface with functional buttons, and ensuring proper access control for admin and registered users. I also integrated a trailer request feature, designed structured HTML templates for movies and cast, and optimized repository hygiene by updating the `.gitignore` file to exclude unnecessary files.

## 5 most relevant commits ⬆️
| Rank | Commit |
|-----:|-----------|
|     1| [Adding page errors for movies and cast when they arent found](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/5e75e3542638097777769de86c7a5a2ae3dbb187)|
|     2| [Fixing buttons for admin and registered users and adding requesting URL trailer in newMovie html](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/4a1510d83603ceeb3ce029c45766e55833934c0b) |
|     3| [Adding Buttons to html templates](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/875b42d8fa2f93459551759c85aeac06e6f2a27f) |
|     4| [Adding HTML templates for Movie and Cast](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/48dda01b3c98bad1842787ab7725c58fe7cc7c27) |
|     5| [Adding .gitignore without any dont deserved file in the repo](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-17/commit/7366f8f7ae5d4e023521c21fdd3235e83e3b2fc8) |

## 5 files in wich I have participated the most 📝
| Rank | File link |
|-----:|-----------|
|     1| file link example|
|     2| file link example |
|     3| file link example |
|     4| file link example|
|     5| file link example        |




## CARLOS
# DESCRIPTION OF THE TASKS DONE ✍️
>Add description here

## 5 most relevant commits ⬆️
| Rank | Commit |
|-----:|-----------|
|     1| Commit example|
|     2| Commit example |
|     3| Commit example |
|     4| Commit example|
|     5| Commit example        |

## 5 files in wich I have participated the most 📝
| Rank | File link |
|-----:|-----------|
|     1| file link example|
|     2| file link example |
|     3| file link example |
|     4| file link example|
|     5| file link example        |








[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/D1C1HU9V)

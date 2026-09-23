Todo App

Project Overview:
This project is a Todo App REST API built using Java and Spring Boot. The project includes Spring Boot, Spring Profiles, PostgreSQL, Spring Data JPA, and CRUD operations.

GitHub Repository:
https://github.com/Israa-Husain/Todo-Application

Technologies Used:
Java
Spring Boot
Maven
PostgreSQL
Postman

Design Decisions:
-Spring Boot used to build the application because it makes it easier to create and run REST APIs.
-Maven used to manage the dependencies.
-PostgreSQL/pgAdmin as the database for storing the application data.
-Created a Category model with id, name, and description.
-Separated the application into Controller, Service, and Repository. The Controller is the presentation, the Service contains the business logic, and the Repository communicates with the database.
-Used REST endpoints with GET, POST, PUT, DELETE.

Reasons Behind the Decisions:
-Spring Boot was used because it provides an easy way to configure and run the application.
-PostgreSQL was used to store the categories.
-The Controller, Service, and Repository structure was used to keep the code organized.
-Spring Data JPA was used because it provides useful database methods.

What Went Right:
GET /hello – Returns a “Hello World!” message.
GET /api/categories – Retrieves all categories.
GET /api/categories/{id} – Retrieves a specific category by its id.
POST /api/categories – Creates a new category.
PUT /api/categories/{id} - Update a specific category by its id.
DELETE /api/categories/{id} - Delete a specific category by its id.

# HeadsOrTailsAPI

## About this project

This project is a REST API for an online Heads or Tails Game. 
- In the game Users or Players must log into the system in order to play games and interact.
- Also, there are admin users with higher permission in order to manage and acces certain endpoint and data about the users.
- All played games are registered in the database and  describe a relation between 2 players. Games will be classified into normal and ranked games.

## UML Entities Diagram

The following UML class diagram show an overview of the proposed database entities structure and relations.

![entities_diagram_basic](https://github.com/user-attachments/assets/581a3322-402c-452c-8361-175847cf8de3)

## Project Setup

First of all, clone the repo into your machine. All the maven dependencies are already properly configured in a POM.xml file.



In order to get the project working, after cloning it, the application.properties file must be configured in order to set up the local databse and other configurations.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/heads-or-tails
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

Run the application through your IDE or building the project into a jar file and executing it.

Finally test the functionality through Postman or any other client.

## Technologies

- Java 21
- Spring Boot 3.4.5
- Spring Security
- Spring Data JPA (Hibernate)
- JWT
- Spring Web
- JUnit 5

## Controllers and routes

Controllers have been divided into MatchControllers, with its respective inherited subclasses, and UserControllers. Each controllers manages the access level after the controller base path.
Routes containing "/public" are public access endpoints, while the routes contaiing "/profile" require authentication. The rest of the routes are only accessible by admin users.

  - **MatchController** : /matches
    - **GET** : /matches/public (with corresponding request params)
    - **GET** : /matches/public/{id}
    - **PATCH** : /matches/{id}
    - **DELETE** : /matches/{id}
    - **RankedMatchController** : /rankedMatches
      - **GET** : /rankedMatches/public (with corresponding request params)
      - **GET** : /rankedMatches/public/{id}
      - **PATCH** : /rankedMatches/{id}
      - **DELETE** : /rankedMatches/{id}
      - **POST** : /rankedMatches
      - **PUT** : /rankedMatches/{id}
  - **RegularMatchController** : /regularMatches
    - **GET** : /regularMatches/public (with corresponding request params)
    - **GET** : /regularMatches/public/{id}
    - **PATCH** : /regularMatches/{id}
    - **DELETE** : /regularMatches/{id}
    - **POST** : /regularMatches
    - **PUT** : /regularMatches/{id}
- **UserController** : /users
    - **GET** : /users/public (with corresponding request params)
    - **GET** : /users/profile
    - **GET** : /users/public/{username}
    - **PATCH** : /regularMatches/{id}
    - **DELETE** : /regularMatches/{id}
    - **POST** : /regularMatches
    - **PUT** : /regularMatches/{id}

As the structure shows, all the endpoints that make any sort of change to the database are admin-only accesible.
It also shows, that all the POST and PUT request for matches are only implemented at derived subclasses routes, in order to avoid problem of abstract classes instantation.

## Extra links

- Trello : https://trello.com/b/VV9GqzQK/headsortailsapi
- Google Slides Presentation : https://docs.google.com/presentation/d/1qjzWJpfmYn7KSrPbCyDaV7ecei2tsyxw9wNiYUHM8lg/edit?usp=drive_link

## Future Work

- Add several payment and products features.
  - Users can also make payments to acquire certain products. Each payment is registered in the database and have a relation with users in order to validate what the user owns. Payments can be made with in game currency or real currency.
  - Each payment is unique and are linked to the bought product.
  - Each specific product is registered in the database. They can be Cosmetic Product or Account Products that can unlock features to the user.
  - The following uml class diagram show the possible extended structure.
  
![entities_diagram](https://github.com/user-attachments/assets/23727ce9-04d6-40a0-a8b3-74f70a43b2b0)

- Update the corresponding user info depending on the results of the matches.
- Frontend as a Unity or Three.js simple game, that make the corresponding requests.

## Resources

- PlantUML : https://plantuml.com/es/
- Baeldung (Spring Boot help) : https://www.baeldung.com/spring-boot

## Team

#### Álvaro González Alcázar
- LinkedIn : https://www.linkedin.com/in/alvarogonzalezal/

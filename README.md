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

## Controllers and routes

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

- Frontend as a Unity or Three.js simple game, that make the corresponding requests.

## Resources

- PlantUML : https://plantuml.com/es/

## Team

#### Álvaro González Alcázar
- LinkedIn : https://www.linkedin.com/in/alvarogonzalezal/

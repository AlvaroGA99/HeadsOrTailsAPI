# HeadsOrTailsAPI

## About this project

This project is a REST API for an online Heads or Tails Game. 
- In the game Users or Players must log into the system in order to play games and interact.
- Also, there are admin users with higher permission in order to manage and acces certain endpoint and data about the users.
- All played games are registered in the database and  describe a relation between 2 players. Games will be classified into normal and ranked games.
- Users can also make payments to acquire certain products. Each payment is registered in the database and have a relation with users in order to validate what the user owns. Payments can be made with in game currency or real currency.
- Each payment is unique and are linked to the bought product.
- Each specific product is registered in the databse. The can be Cosmetic Product or Account Products that can unlock features to the user.


## UML Entities Diagram

The following UML class diagran show an overview of the proposed database entities structure and relations.

![entities_diagram](https://github.com/user-attachments/assets/23727ce9-04d6-40a0-a8b3-74f70a43b2b0)

# Todo: Be Awesome - Backend 

This is a simple full-stck to-do app that allows for the user to create new goals with the ability to complete them. It also served as an exercise in practising full-stack development. 

## Build Steps

To run this locally, you will need to do the following:

1. Required software and dependencies:
     - [ ] Java Development Kit: https://www.oracle.com/au/java/technologies/downloads/
     - [ ] Apache Maven: https://maven.apache.org/download.cgi
     - [ ] MySQL: https://dev.mysql.com/downloads/installer/

2. Clone to repository to your local machine and run the following commands:

```bash
git clone https://github.com/TimBroderick44/beAwesome-Backend.git
```

```bash
cd beAwesome-Backend
```

3. Set up access to your database:
   - Using the mySQL CLI (or the mySQL workbench) to login to your account:

```bash
mysql -u root -p
```

4. Create a new database for use with the app:

```sql
CREATE DATABASE your_database_name;
```

5. Configure the application properties:

- In the src/main/resources directory, create 'application.properties' and add the following:

```java
spring.application.name=tickit-backend
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

6. Build (the 'clean' keyword ensures that it is built from scratch):

```bash
mvn clean install
```

7.  Go to the following URL and check that it is up and running

```
http://localhost:8080/todos
```

8. Upon confirmation that it is up and running, access the Swagger API documentation:

```
http://localhost:8080/swagger-ui/index.html
```

9. Enjoy! Test the endpoints with Postman and have fun! 

## Please keep in mind: 

You will require access to the frontend. The repository to the frontend is available here: https://github.com/TimBroderick44/beAwesome-Frontend

## What did I use to create it?

This project uses:

-   SpringBoot
-   Java
-   mySQL 
-   Swagger
-   Log4j

## Features of the Project:

-  Performs basic CRUD operations
-  Logs all requests (allows for prehandling and posthandling)
-  Handles all errors
-  Swagger provides documentation on the API and presents all endpoints available. 

<h1 style="font-weight: 900"> Here are some of the lessons I learnt:</h1>

### Building on my knowledge:

-   This project allowed for further exposure to building a backend in SpringBoot. I felt much more comfortable building the resources necessary (i.e. controller, service, repository, etc.) and was able to implement the logic more efficiently. 
  
### New Features like Swagger, Log4j, etc. 

- These was all new to me; however, implementing them was relatively easy. For minimal effort, we are able to log requests (with the logs highly configurable) and have simple documentation on our endpoints. I will definitely be utilising these again in future projects. The feedback provided is incredibly helpful when in development.

## What I want to include in the future:

## Have more functionality:

-   [ ] Include more entities - e.g. Users, categories, etc.
-   [ ] Allow for filtering and search functionality
-   [ ] Include authorization (including roles)
-   [ ] Have more business logic here and less on the frontend. 

## Thank You!

Thank you for taking the time to look at this project. I really hope you enjoy it.
Feel free to reach out and ask any questions.

[Tim Broderick]

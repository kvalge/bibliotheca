# BIBLIOTHECA

Library application which allows to manage users, books and lending data.  
Used IDE: IntelliJ 2022.3.
Spring app and postgres db images in Docker Hub: https://hub.docker.com/r/kvdocker123/bibliotheca.
To run containers:
docker run --name bibliotheca-spring -p 8080:8080 -d kvdocker123/bibliotheca:spring
docker run --name bibliotheca-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d kvdocker123/bibliotheca
:postgres

### Project structure
Domain directory to manage functionalities.  
Infrastructure directory for exception classes.  
Validation directory to create user input validation methods.  
db directory to create tables and insert data for database.  
Javadoc directory with index.html file to get general overview of project.  

### Main functionalities
1. Add and edit library user.  
Role: user can have role as librarian or library user.  
User: branching out from role every user have username and password. Branching to librarian and library-user.  
Librarian: to add, return all and delete librarian (id code as a unique identifier could be replaced with 
the number of a certificate of employment).  
Library-user: to add, return all, return by id code (could be replaced with the library card number), delete 
library user. Functionalities carried out by librarian.  
2. Add and edit book.  
Book: to add a new book, return all, find by name, update all book data, update the quantity of copies,
delete a book. Can be used by librarian and library user.  
3. Add and edit lending.  
Lending: to lend book out, update lending on book return, returning a lending list with overdue deadlines. 
Carried out by librarian.

### Database
Used database PostgreSQL.  
Database credentials in application.properties file on lines 11-12.  
Added lending.uml file to db for tables diagram.

### Testing
Same PostgreSQL database is used for unit testing deleting entries after each test.  
For functionalities' testing is used Swagger (http://localhost:8080/documentation/swagger-ui.html).  

### VCS
For version control is used GitHub.  
IntelliJ IDEA task integration with GitHub is used to create task log.  




--------------------------------------------------  
  

### Reference Documentation
For further reference, please consider the following sections:
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.8-SNAPSHOT/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.8-SNAPSHOT/gradle-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.8-SNAPSHOT/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.8-SNAPSHOT/reference/htmlsingle/#web)
* [Validation](https://docs.spring.io/spring-boot/docs/2.7.8-SNAPSHOT/reference/htmlsingle/#io.validation)

### Guides
The following guides illustrate how to use some features concretely:
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Additional Links
These additional references should also help you:
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


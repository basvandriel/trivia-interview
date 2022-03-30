# trivia-interview
Made in Spring Boot.

## Setting up

In the `src/main/resources` directory, create a file called `application.properties`. In here, paste the following.

```
spring.datasource.url=jdbc:postgresql://localhost:5432/trivia
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=?
spring.datasource.password=?
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update
```

Replace the database credentials with yours.

## Starting up

Using Windows? Open up a terminal and run `./mvnw.cmd spring-boot:run. Linux user? `./mvnw spring-boot:run
Docker support coming later!
# User Account Service

The **User Account Service** is a Spring Boot-based microservice designed to manage user accounts and banking operations. It leverages modern Java development practices, database migrations, and RESTful APIs to deliver a scalable and maintainable service.

## Features

- **RESTful APIs**: Built using Spring Boot's web starter for creating high-performing web services.
- **Database Management**: Integration with Flyway for database migrations and Oracle JDBC for database communication.
- **MyBatis**: Simplifies database interactions with MyBatis as the ORM framework.
- **Testing**: Includes comprehensive testing support with Spring Boot Starter Test, MyBatis Test, and an in-memory H2 database for unit testing.
- **Developer Tools**: Hot reloading and runtime enhancements with Spring Boot DevTools.
- **Code Simplification**: Uses Lombok to reduce boilerplate code.

---

## Dependencies

The project includes the following key dependencies:

### Core Dependencies
- **Spring Boot Starter Web**: For building RESTful APIs.
- **MyBatis Spring Boot Starter**: ORM framework for database interactions.
- **Flyway Core & Flyway Database Oracle**: Database versioning and migration.
- **Oracle JDBC Driver (ojdbc11)**: Communication with Oracle databases.

### Testing
- **Spring Boot Starter Test**: Includes JUnit 5 and MockMvc for testing.
- **MyBatis Spring Boot Starter Test**: Testing support for MyBatis.
- **H2 Database**: In-memory database for lightweight testing.

### Developer Tools
- **Spring Boot DevTools**: Enables hot reloading for a better development experience.
- **Lombok**: Reduces boilerplate code for model classes.

---

## Prerequisites

To run this project, you need the following:

1. **JDK 17** or higher
2. **Maven 3.8+**
3. **Oracle Database** (for production)
4. **H2 Database** (for testing)

---

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/Balsalobre/user-account-service.git
cd user-account-service
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start by default on [http://localhost:8080](http://localhost:8080).

---

## Database Migration

This service uses Flyway for database versioning and migrations. Place your migration scripts in the `src/main/resources/db/migration` directory following Flyway's naming convention (`V1__Initial_Setup.sql`, etc.).

---

## Testing

The project includes unit and integration tests with the following setup:

1. **H2 Database**: Used for in-memory testing.
2. **Spring Boot Starter Test**: For writing and running tests with JUnit 5.
3. **MyBatis Spring Boot Starter Test**: For testing MyBatis configurations and queries.

Run all tests with:

```bash
mvn test
```

---

## Contributing

Contributions are welcome! Feel free to submit a pull request or open an issue for bug fixes, feature requests, or improvements.

---

## License

This project is maintained under the standard licensing terms. Add your license details here.

---

If you need me to add more sections or details, feel free to let me know!
# Spring Boot Modular Monolith Service

[![Java 17](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

A robust Spring Boot application built using a **Modular Monolith** approach and **Hexagonal Architecture**. This service handles secure RESTful operations, manages distributed caching with Redis, and utilizes a scheduled task system to synchronize data from external APIs into MongoDB.

## 🚀 Features

-   **Hexagonal Architecture:** Strict separation of concerns (Domain, Application, Infrastructure).
-   **Security:** Stateless authentication using Spring Security and JWT.
-   **Scheduled Tasks:** Periodically fetches data from an external API using `RestTemplate` and persists it to MongoDB.
-   **Reactive Data:** MongoDB integration.
-   **Caching:** Redis implementation using Jedis for high-performance caching.
-   **Observability:** Comprehensive logging with Log4j2 and API documentation via Swagger.
-   **Containerization:** Full Docker support with Docker Compose for the App, Mongo, MongoExpress, Redis, and Redis-Commander.

## 🛠 Tech Stack

### Core & Frameworks
*   **Language:** Java 21
*   **Framework:** Spring Boot (Web, Validation, Security, Scheduler)
*   **Boilerplate reduction:** Lombok

### Data & Storage
*   **Database:** MongoDB
*   **DB Management:** MongoExpress (Web-based MongoDB admin)
*   **Caching:** Redis & Jedis
*   **Cache Management:** Redis-Commander

### Architecture & Design
*   **Pattern:** Modular Monolith (Can Be Microservices Later If Needed)
*   **Style:** Hexagonal Architecture (Ports and Adapters)

### Testing
*   **Unit Testing:** JUnit 5, Mockito, AssertJ
*   **Integration Testing:** Testcontainers, Spring Boot Test
*   **Mocking External APIs:** Wiremock
*   **Async Testing:** Awaitility
*   **MVC Testing:** MockMvc

### DevOps & Tools
*   **Build Tool:** Maven
*   **Containerization:** Docker, Docker Desktop & Docker Compose
*   **CI/CD:** GitHub Actions
*   **IDE:** IntelliJ IDEA Ultimate
*   **Version Control:** Git

---

## 🏗 Architecture

This project follows **Hexagonal Architecture** (Ports and Adapters) within a **Modular Monolith** structure.

## ⚙️ Getting Started

### Prerequisites
*   Java 21 JDK installed
*   Docker & Docker Desktop installed and running
*   Maven installed


### Accessing the Services

Once the containers are running, you can access the various services at:

| Service | URL | Description |
| :--- | :--- | :--- |
| **API API** | `http://localhost:8080` | Main Application |
| **Swagger UI** | `http://localhost:8080/swagger-ui/index.html` | API Documentation |
| **MongoExpress** | `http://localhost:8081` | MongoDB GUI Admin |
| **Redis Commander** | `http://localhost:8082` | Redis GUI Admin |

---


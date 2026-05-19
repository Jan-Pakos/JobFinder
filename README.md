# ABOUT THE PROJECT

[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

A robust Spring Boot application built using a **Modular Monolith** approach and **Hexagonal Architecture**. This service handles secure RESTful operations, manages distributed caching with Redis, and utilizes a scheduled task system to synchronize data from external APIs into MongoDB.

## I included a .env file with environment variables for your convience, I know that this should NOT be done in a real production API (Especially in a public repo) ##

Healthcheck: http://localhost:8000/actuator/health

Use Postman or other similar tool.

1. In order to access the endpoints you need a bearer token.
3. Send a POST request with to http://localhost:8000/register a  username and password in a json like this:
   {
  "username": "string",
  "password": "string"
   }
replace both "string" with a username and password of choice.
Example:

<img width="526" height="204" alt="Screenshot 2026-05-18 at 16 55 21" src="https://github.com/user-attachments/assets/c8720b1b-753b-4e7a-8d18-59ca741fbb9c" />



5. Then make a POST request to http://localhost:8000/token
   You will get back a json like this:
   {
  "username": "YOURUSERNAME",
  "token": "YOURTOKEN"
   }
   copy the token for later use.
   Result:
   
   <img width="588" height="421" alt="Screenshot 2026-05-18 at 17 41 44" src="https://github.com/user-attachments/assets/a4f1c396-d7b5-4728-970f-371fe306c455" />



Now you can make a GET request to http://localhost:8000/offers
This will show you all job offers in the system
Example:

<img width="585" height="243" alt="Screenshot 2026-05-18 at 17 41 12" src="https://github.com/user-attachments/assets/d6076f09-7446-4290-8d2a-d9aa5803c803" />



Or make a POST request to http://localhost:8000/offers with json body:
{
  "title": "string",
  "company": "string",
  "salary": "string",
  "offerUrl": "string"
}
with your own job.


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



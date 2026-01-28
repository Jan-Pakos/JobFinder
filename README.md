# ABOUT THE PROJECT

[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

A robust Spring Boot application built using a **Modular Monolith** approach and **Hexagonal Architecture**. This service handles secure RESTful operations, manages distributed caching with Redis, and utilizes a scheduled task system to synchronize data from external APIs into MongoDB.

It’s live on AWS. I’m working on the React frontend. In the meantime, you can test it like this:
Go to Postman OR online API testing tool like this: https://reqbin.com

1. In order to access the endpoints you need a bearer token.
2. To http://ec2-100-53-53-35.compute-1.amazonaws.com:8000/register
3. Send a POST request with a  username and password in a json like this:
   {
  "username": "string",
  "password": "string"
   }
replace both "string" with a username and password of choice.
Example:
<img width="787" height="372" alt="Screenshot 2026-01-28 at 13 03 34" src="https://github.com/user-attachments/assets/829fe3b7-ef22-4c4c-96a7-c1068b1da86b" />


5. Then make a POST request to http://ec2-100-53-53-35.compute-1.amazonaws.com:8000/token
   You will get back a json like this:
   {
  "username": "YOURUSERNAME",
  "token": "YOURTOKEN"
   }
   copy the token for later use.
   Result:
   <img width="774" height="173" alt="Screenshot 2026-01-28 at 13 04 17" src="https://github.com/user-attachments/assets/cfc2e32e-b18f-4c3f-9726-7a0393bfa216" />


Now you can make a GET request to http://ec2-100-53-53-35.compute-1.amazonaws.com:8000/offers
This will show you all job offers in the system
Example:
<img width="783" height="323" alt="Screenshot 2026-01-28 at 13 04 58" src="https://github.com/user-attachments/assets/c22d7030-8215-4d71-8c17-fca7f9752d88" />


Or make a POST request to http://ec2-100-53-53-35.compute-1.amazonaws.com:8000/offers with json body:
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


### Accessing the Services

Once the containers are running, you can access the various services at:

| Service | URL | Description |
| :--- | :--- | :--- |
| **API API** | `http://localhost:8080` | Main Application |
| **Swagger UI** | `http://localhost:8080/swagger-ui/index.html` | API Documentation |
| **MongoExpress** | `http://localhost:8081` | MongoDB GUI Admin |
| **Redis Commander** | `http://localhost:8082` | Redis GUI Admin |

---


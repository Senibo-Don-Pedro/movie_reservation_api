# 🎬 Movie Reservation System API

A production-grade backend API for a cinema booking system, built with **Java 21** and **Spring Boot 3**.

This project implements a robust architecture to handle movie scheduling, seat inventory, and concurrent ticket reservations. It was built following the requirements from [roadmap.sh/projects/movie-reservation-system](https://roadmap.sh/projects/movie-reservation-system).

## 🚀 Key Features

* **Stateless Authentication:** Secure login/registration using **JWT (JSON Web Tokens)** and Spring Security 6.
* **Concurrency Control:** Custom algorithm to prevent **Double-Booking** of seats and **Overlapping Showtimes** in the same room.
* **Dynamic Inventory:** Automatic generation of seat layouts (Rows/Columns) when creating theater rooms.
* **Transactional Integrity:** Uses `@Transactional` to ensure data consistency across complex booking operations (Reservation -> Tickets).
* **Validation & Error Handling:** Global Exception Handler ensuring clean, standardized JSON error responses (RFC 7807 style).
* **API Documentation:** Fully documented using Swagger UI / OpenAPI 3.

## 🛠️ Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.3.5 (Spring Web, Data JPA, Security, Validation)
* **Database:** PostgreSQL 16
* **Testing:** JUnit 5, Mockito
* **Documentation:** SpringDoc OpenAPI (Swagger)
* **Tools:** Maven, Lombok, Docker (optional)

## 🏗️ Architecture & Database

The system uses a **Normalized Relational Schema** to handle complex relationships:

* **Users:** System actors (Admins/Customers).
* **Movies:** Metadata (Title, Duration, Genre).
* **Rooms & Seats:** Physical inventory.
* **Showtimes:** The bridge between a Movie, a Room, and a Time.
* **Reservations & Tickets:** The transaction layer linking Users to specific Seats for a specific Showtime.

## ⚙️ Getting Started

### Prerequisites
* Java 21 SDK
* PostgreSQL running on localhost:5432

### Installation

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/yourusername/movie-reservation-system.git](https://github.com/yourusername/movie-reservation-system.git)
    cd movie-reservation-system
    ```

2.  **Configure Database**
    Create a database named `moviereservation` in PostgreSQL.
    ```sql
    CREATE DATABASE moviereservation;
    ```
    *Update `src/main/resources/application.properties` with your DB username/password if different from default.*

3.  **Run the Application**
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Explore the API**
    Once running, open your browser to access the Swagger UI:
    * 👉 **http://localhost:8080/swagger-ui/index.html**

## 🧪 Testing the Flow

1.  **Register:** Create a user via `/api/v1/auth/register`.
2.  **Authorize:** Copy the JWT token and click the "Authorize" button in Swagger.
3.  **Setup:** Create a Movie and a Room (Seats are auto-generated!).
4.  **Schedule:** Create a Showtime linking the Movie and Room.
5.  **Book:** Use the `/api/v1/reservations` endpoint to book specific seats.

---
*Built with ❤️ and Java.*
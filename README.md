# 📦 Order Management System

## 🧾 Overview
This project is a **Spring Boot application** for managing orders.  
It demonstrates a modular and clean architecture that separates responsibilities between data handling, business logic, and presentation layers.

---

## 🏗️ Project Structure

```
app/
├── controller/       # Handles incoming HTTP requests and defines REST endpoints
├── service/          # Contains business logic and coordinates between controller and repository
├── repository/       # Responsible for data access and persistence
├── model/            # Defines the application's data models/entities
└── OrderManagementSystemApplication.java  # Main Spring Boot entry point
```

---

## ⚙️ Architectural Decisions

- **Multiple Controllers**  
  Each main entity has its own controller (e.g., `OrderController`, `CustomerController`) to ensure a clear separation of responsibilities and improve maintainability.

- **Dedicated Services per main Entity**  
  Each service encapsulates business logic specific to its domain (e.g., `OrderService` handles order-related rules).  
  This modular structure makes testing and future extensions simpler and more reliable.

- **Repositories**  
  Instead of using a database, the application stores data **in memory**.  
  Repository classes manage data using maps.  
  This approach simplifies setup and makes the application lightweight for demonstration and testing purposes.

- **Models / Entities**  
  The model layer defines all entities as simple Java classes.  
  They represent the data objects used by services and controllers.
---

## 🔄 Application Flow

1. **Client request** hits a web endpoint defined in the **controller** layer.
2. The **controller** validates input and delegates logic to the appropriate **service**.
3. The **service** executes business logic and interacts with the **repository** for data handling.
4. The **repository** manages in-memory collections and returns results to the **service**.
5. The **controller** sends the processed response back to the client (usually as JSON).

## 💡 Design Justification

- Follows the **Separation of Concerns** principle for modularity and clarity.
- Smaller, focused classes improve **readability, testing, and maintenance**.
- Adheres to **Spring Boot conventions**, making the project consistent and easy for new developers to understand.
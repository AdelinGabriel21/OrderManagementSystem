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
  Each entity has its own controller (e.g., `OrderController`, `CustomerController`) to ensure a clear separation of responsibilities and improve maintainability.

- **Dedicated Services per Entity**  
  Each service encapsulates business logic specific to its domain (e.g., `OrderService` handles order-related rules).  
  This modular structure makes testing and future extensions simpler and more reliable.

- **Repositories**  
  We use **Spring Data JPA** repositories to handle persistence.  
  This approach eliminates boilerplate code and keeps data operations consistent and declarative.

- **Models / Entities**  
  The model layer defines all entities as simple POJOs annotated with `@Entity`.  
  These represent database tables and provide the foundation for the repository layer.

---

## 🔄 Application Flow

1. **Client request** hits a REST endpoint defined in the **controller** layer.
2. The **controller** validates input and delegates logic to the appropriate **service**.
3. The **service** executes business logic and interacts with the **repository** for data operations.
4. The **repository** communicates with the database and returns results to the **service**.
5. The **controller** sends the processed response back to the client (usually as JSON).

---

## 🚀 Running the Application

1. Ensure you have **Java 17+** and **Maven** installed.
2. Build the project:
   ```bash
   mvn clean package
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Open your browser and navigate to [http://localhost:8080](http://localhost:8080).

---

## 💡 Design Justification

- Follows the **Separation of Concerns** principle for modularity and clarity.
- Smaller, focused classes improve **readability, testing, and maintenance**.
- Adheres to **Spring Boot conventions**, making the project consistent and easy for new developers to understand.

---

## 🧭 Future Improvements

- Introduce **DTOs and mappers** to separate API models from entities.
- Add **global exception handling** using `@ControllerAdvice`.
- Implement **Swagger/OpenAPI documentation** for interactive API exploration.
- Expand test coverage for controllers and services.


# 📦 Order Management System

## 🧾 Übersicht
Dieses Projekt ist eine **Spring Boot Anwendung** zur Verwaltung von Bestellungen.  
Es demonstriert eine modulare und saubere Architektur, die Verantwortlichkeiten klar zwischen Datenverarbeitung, Geschäftslogik und Präsentation trennt.

---

## 🏗️ Projektstruktur

```
app/
├── controller/       # Verarbeitet HTTP-Anfragen und definiert REST-Endpunkte
├── service/          # Enthält Geschäftslogik und koordiniert zwischen Controller und Repository
├── repository/       # Zuständig für Datenzugriff und -speicherung
├── model/            # Definiert die Datenmodelle/Entitäten der Anwendung
└── OrderManagementSystemApplication.java  # Hauptklasse und Einstiegspunkt der Anwendung
```

---

## ⚙️ Architekturentscheidungen

- **Mehrere Controller**  
  Jede Entität hat ihren eigenen Controller (z. B. `OrderController`, `CustomerController`), um klare Verantwortlichkeiten zu gewährleisten und die Wartbarkeit zu verbessern.

- **Eigene Services pro Entität**  
  Jeder Service enthält die spezifische Geschäftslogik für seinen Bereich (z. B. `OrderService` für Bestellungen).  
  Diese modulare Struktur erleichtert das Testen und spätere Erweiterungen.

- **Repositories**  
  Es werden **Spring Data JPA** Repositories verwendet, um den Datenzugriff zu vereinfachen.  
  Dadurch wird Boilerplate-Code reduziert und der Datenzugriff bleibt konsistent und deklarativ.

- **Modelle / Entitäten**  
  Die Modelle sind einfache POJOs mit der Annotation `@Entity`.  
  Sie repräsentieren Datenbanktabellen und bilden die Grundlage für die Repositories.

---

## 🔄 Ablauf der Anwendung

1. Eine **Client-Anfrage** trifft auf einen REST-Endpunkt im **Controller**.
2. Der **Controller** prüft die Eingaben und ruft den entsprechenden **Service** auf.
3. Der **Service** führt die Geschäftslogik aus und verwendet das **Repository** für Datenoperationen.
4. Das **Repository** kommuniziert mit der Datenbank und gibt Ergebnisse zurück.
5. Der **Controller** sendet die Antwort (in der Regel JSON) an den Client zurück.

---

## 🚀 Anwendung starten

1. Stelle sicher, dass **Java 17+** und **Maven** installiert sind.
2. Projekt bauen:
   ```bash
   mvn clean package
   ```
3. Anwendung starten:
   ```bash
   mvn spring-boot:run
   ```
4. Die Anwendung läuft unter [http://localhost:8080](http://localhost:8080).

---

## 💡 Begründung der Architektur

- Befolgt das Prinzip der **Trennung von Verantwortlichkeiten** für bessere Modularität und Übersichtlichkeit.
- Kleine, fokussierte Klassen verbessern **Lesbarkeit, Testbarkeit und Wartbarkeit**.
- Hält sich an **Spring Boot Konventionen**, was den Einstieg für neue Entwickler erleichtert.

---

## 🧭 Zukünftige Verbesserungen

- Einführung von **DTOs und Mappers**, um API-Modelle von Entitäten zu trennen.
- Implementierung einer **globalen Fehlerbehandlung** mit `@ControllerAdvice`.
- Hinzufügen einer **Swagger/OpenAPI-Dokumentation** für eine bessere API-Nutzung.
- Ausbau der Testabdeckung für Controller und Services.

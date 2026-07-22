# Digital Factory

Reactive API for student management based on hexagonal architecture (ports and adapters).

## Tech Stack

- Java 21
- Spring Boot 3.4.1 (WebFlux + R2DBC)
- H2 in-memory (reactive database)
- Project Lombok
- MapStruct 1.5.5.Final
- JaCoCo 0.8.14
- Maven (multi-module)

## Modules

```
digital-factory (parent pom)
├── digital-factory-domain      — Entities, ports, and exceptions
├── digital-factory-application — Use cases
├── digital-factory-infrastructure — Adapters (controllers, repositories)
└── digital-factory-boot        — Application entry point
```

Module dependencies follow the hexagonal architecture flow: `boot` → `application` → `domain` and `boot` → `infrastructure` → `domain`. Domain knows nothing about other modules.

## Endpoints

| Method | Route               | Description                    |
|--------|---------------------|--------------------------------|
| GET    | /api/v1/students    | Get all active students        |
| POST   | /api/v1/students    | Register a new student         |

### POST /api/v1/students

Payload:

```json
{
  "id": "72332211",
  "name": "Paúl",
  "lastname": "Guevara",
  "status": "active",
  "age": 30
}
```

## Run

```bash
mvn clean install -DskipTests
mvn spring-boot:run -pl digital-factory-boot
```

## Build

Requires Java 21 and Maven 3.8+.

```bash
mvn clean package
```

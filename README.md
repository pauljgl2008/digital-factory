# Digital Factory

API reactiva para la gestión de alumnos basada en una arquitectura hexagonal (puertos y adaptadores).

## Stack tecnológico

- Java 21
- Spring Boot 3.4.1 (WebFlux + R2DBC)
- H2 en memoria (base de datos reactiva)
- Project Lombok
- MapStruct 1.5.5.Final
- JaCoCo 0.8.14
- Maven (multi-módulo)

## Módulos

```
digital-factory (pom padre)
├── digital-factory-domain      — Entidades, puertos y excepciones
├── digital-factory-application — Casos de uso
├── digital-factory-infrastructure — Adaptadores (controladores, repositorios)
└── digital-factory-boot        — Punto de entrada de la aplicación
```

Las dependencias entre módulos siguen el flujo de la arquitectura hexagonal: `boot` → `application` → `domain` y `boot` → `infrastructure` → `domain`. El dominio no conoce a ningún otro módulo.

## Endpoints

| Método | Ruta               | Descripción                      |
|--------|--------------------|----------------------------------|
| GET    | /api/v1/alumnos    | Obtiene todos los alumnos activos |
| POST   | /api/v1/alumnos    | Registra un nuevo alumno          |

### POST /api/v1/alumnos

Payload:

```json
{
  "id": "72332211",
  "nombre": "Paúl",
  "apellido": "Guevara",
  "estado": "activo",
  "edad": 30
}
```

## Ejecución

```bash
mvn clean install -DskipTests
mvn spring-boot:run -pl digital-factory-boot
```

## Construcción

El proyecto requiere Java 21 y Maven 3.8+.

```bash
mvn clean package
```

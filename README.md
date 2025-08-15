# Challenge Literalura

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)

## Descripción

**Literalura** es una aplicación de consola desarrollada como parte del Challenge G8 del programa **ONE (Oracle Next Education)** en colaboración con **Alura**. Este proyecto consolida los conocimientos adquiridos en **Java**, **Spring Framework** y **Gestión de Bases de Datos**.

La aplicación se conecta a la API de [Gutendx](https://gutendx.com/) para buscar y almacenar información sobre libros y autores en una base de datos PostgreSQL, permitiendo posteriormente realizar consultas y filtros sobre los datos registrados.

## Características Principales

-  **Búsqueda de libros** a través de la API Gutendx
-  **Persistencia de datos** en PostgreSQL
-  **Gestión de libros y autores**
-  **Filtrado por idiomas**
-  **Consultas de autores por período**
-  **Interfaz de consola intuitiva**

##  Tecnologías Utilizadas

- **Java** - Lenguaje de programación principal
- **Spring Boot** - Framework para el desarrollo de la aplicación
- **Spring Data JPA** - Para la gestión de datos y persistencia
- **PostgreSQL** - Sistema de gestión de base de datos
- **API Gutendx** - Fuente de datos de libros del Proyecto Gutenberg

##  Funcionalidades

La aplicación presenta un menú interactivo con las siguientes opciones:

###  Menú Principal

1. **Buscar libros por título**
   - Conecta con la API Gutendx
   - Guarda automáticamente en la base de datos
   - Maneja información de libros y autores

2. **Listar libros registrados**
   - Muestra todos los libros almacenados
   - Información detallada de cada libro

3. **Listar autores registrados**
   - Catálogo completo de autores en la base de datos
   - Datos biográficos disponibles

4. **Listar autores vivos en un determinado año**
   - Filtro por período histórico específico
   - Consulta basada en fechas de nacimiento y fallecimiento

5. **Listar libros por idioma**
   - Clasificación por idiomas disponibles
   - Soporte para múltiples idiomas

## Arquitectura del Proyecto

```
src/
├── main/java/com/alura/literalura/
│   ├── model/          # Entidades JPA y Record JSON Mapping
│   ├── repository/     # Repositorios Spring Data
│   ├── service/        # Lógica de negocio
│   └── LiteraluraApplication.java
└── resources/
    └── application.properties
```

## 📋 Prerrequisitos

- **Java 17** o superior
- **PostgreSQL 12** o superior
- **Maven 3.6** o superior
- **Jackson 2.16.0** o superior

## Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone [https://github.com/tu-usuario/literalura.git](https://github.com/Sodi0/challenge-literalura.git)
```

### 2. Configurar la base de datos
```sql
CREATE DATABASE literalura;
```

### 3. Configurar application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
---

## Guía de Uso

1. **Inicio**: Ejecuta la aplicación y aparecerá el menú principal
2. **Primera búsqueda**: Usa la opción 1 para buscar y registrar tu primer libro
3. **Exploración**: Una vez tengas datos, explora las demás opciones del menú
4. **Navegación**: Usa los números del 1-5 para navegar y 0 para salir

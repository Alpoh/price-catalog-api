# Price Catalog API

## Descripción
La aplicación **Price Catalog API** es un servicio REST construido con Spring Boot que permite consultar el precio aplicable de un producto para una marca específica en una fecha y hora dadas. Los precios son inicializados en una base de datos en memoria (H2) y se elige el precio basado en la prioridad cuando hay solapamiento en los rangos de fechas.

---

## Prerrequisitos

1. **Java 17**:
    - Necesario para compilar y ejecutar el proyecto.
    - [Descargar Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

2. **Gradle**:
    - Utilizado para la construcción y gestión del proyecto.
    - [Instalar Gradle](https://gradle.org/install/)

3. **Docker** (opcional):
    - Para ejecutar la aplicación en contenedores.
    - [Instalar Docker](https://docs.docker.com/get-docker/)

4. **Docker Compose** (opcional):
    - Para gestionar múltiples contenedores Docker.
    - [Instalar Docker Compose](https://docs.docker.com/compose/install/)

---

## Construcción y Ejecución con Gradle

1. **Compilar el proyecto**:
   ```bash
   ./gradlew build
   ```

2. **Ejecutar la aplicación**:
   ```bash
   ./gradlew bootRun
   ```

3. **Acceso al servicio**:
    - URL: [http://localhost:8080/prices](http://localhost:8080/prices)

4. **Parámetros del endpoint**:
    - `applicationDate` (String): Fecha y hora en formato `yyyy-MM-dd HH:mm:ss`.
    - `productId` (Long): Identificador del producto.
    - `brandId` (Long): Identificador de la marca.

Ejemplo de petición:
```bash
curl "http://localhost:8080/prices?applicationDate=2020-06-14%2010:00:00&productId=35455&brandId=1"
```

---

## Uso con Docker y Docker Compose

### Construcción de la imagen Docker
1. Crear el archivo JAR del proyecto:
   ```bash
   ./gradlew build
   ```

2. Crear la imagen Docker:
   ```bash
   docker build -t price-catalog-api .
   ```

### Ejecución con Docker
1. Ejecutar el contenedor:
   ```bash
   docker run -p 8080:8080 price-catalog-api
   ```

2. Acceso al servicio:
    - URL: [http://localhost:8080/prices](http://localhost:8080/prices)

### Uso con Docker Compose
1. Ejecutar el proyecto con Docker Compose:
   ```bash
   docker-compose up --build
   ```

2. Acceso al servicio:
    - URL: [http://localhost:8080/prices](http://localhost:8080/prices)

---

## Notas
- El servicio también expone una consola H2 accesible en: [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
- Las credenciales para H2 son:
    - Usuario: `sa`
    - Contraseña: *(vacío)*


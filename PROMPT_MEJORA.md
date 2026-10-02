# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/financial/comments/infrastructure/controller/CommentController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/financial/comments/infrastructure/controller/CommentControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/comments/CommentsApplication.java` — `ApplicationProperties.getCircuitBreaker`: Se invoca `getCircuitBreaker` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/dto/CommentResponse.java` — `Comment.id`: Se invoca `id` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/dto/CommentResponse.java` — `Comment.productId`: Se invoca `productId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/dto/CommentResponse.java` — `Comment.author`: Se invoca `author` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/dto/CommentResponse.java` — `Comment.content`: Se invoca `content` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/dto/CommentResponse.java` — `Comment.status`: Se invoca `status` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/dto/CommentResponse.java` — `Comment.createdAt`: Se invoca `createdAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/dto/CommentResponse.java` — `Comment.idempotencyKey`: Se invoca `idempotencyKey` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/application/usecase/CreateCommentUseCase.java` — `Comment.id`: Se invoca `id` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/application/usecase/CreateCommentUseCase.java` — `CommentValidator.isValid`: Se invoca `isValid` sobre `CommentValidator`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/application/validator/CommentValidator.java` — `ApplicationProperties.getOffensiveWords`: Se invoca `getOffensiveWords` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/controller/CommentController.java` — `Comment.id`: Se invoca `id` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/controller/CommentController.java` — `Comment.productId`: Se invoca `productId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/controller/CommentController.java` — `Comment.author`: Se invoca `author` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/controller/CommentController.java` — `Comment.content`: Se invoca `content` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/controller/CommentController.java` — `Comment.status`: Se invoca `status` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/controller/CommentController.java` — `Comment.createdAt`: Se invoca `createdAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/comments/infrastructure/controller/CommentController.java` — `Comment.idempotencyKey`: Se invoca `idempotencyKey` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/comments/application/usecase/CreateCommentUseCaseTest.java` — `Comment.productId`: Se invoca `productId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/comments/application/usecase/CreateCommentUseCaseTest.java` — `Comment.author`: Se invoca `author` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/comments/application/usecase/CreateCommentUseCaseTest.java` — `Comment.content`: Se invoca `content` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/comments/application/usecase/CreateCommentUseCaseTest.java` — `Comment.idempotencyKey`: Se invoca `idempotencyKey` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/comments/application/usecase/CreateCommentUseCaseTest.java` — `Comment.status`: Se invoca `status` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Spring, Tecnología Java, Senior L2

### Brecha de conocimiento
Comprueba manejo de errores y validaciones

### Reto
- Tema: E2E prod comments 1790905766
- Seniority: senior-l2
- Tipo: practical
- Título: Manejo de Errores y Validaciones en Comentarios de Producción
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Implementación de Validaciones Básicas — objetivo: Asegurar que los comentarios cumplan con las reglas de validación básicas antes de ser persistidos. — entregable (NO resolver): Sistema que aplica validaciones básicas a los comentarios antes de persistirlos.
- Fase 2: Manejo de Errores y Registro de Comentarios — objetivo: Implementar un mecanismo para manejar errores y registrar comentarios con idempotencia. — entregable (NO resolver): Sistema que maneja errores y registra comentarios con idempotencia.
- Fase 3: Optimización y Refactorización — objetivo: Optimizar el rendimiento y refactorizar el código para mejorar su mantenibilidad. — entregable (NO resolver): Sistema optimizado y refactorizado para manejar un mayor volumen de comentarios.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.financial</groupId>
    <artifactId>comments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>comments</name>
    <description>Sistema de manejo de comentarios para productos financieros</description>

    <properties>
        <java.version>21</java.version>
        <lombok.version>1.18.30</lombok.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/financial/comments/CommentsApplication.java ===
package com.financial.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.time.Duration;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
public class CommentsApplication {
    private final ApplicationProperties applicationProperties;

    public CommentsApplication(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        validateApplicationProperties();
    }

    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(applicationProperties.getCircuitBreaker().getFailureRateThreshold())
                .waitDurationInOpenState(Duration.ofMillis(applicationProperties.getCircuitBreaker().getWaitDurationInOpenState()))
                .slidingWindowSize(applicationProperties.getCircuitBreaker().getSlidingWindowSize())
                .build();
        return CircuitBreakerRegistry.of(circuitBreakerConfig);
    }

    private void validateApplicationProperties() {
        if (applicationProperties.getCircuitBreaker().getFailureRateThreshold() <= 0 ||
            applicationProperties.getCircuitBreaker().getWaitDurationInOpenState() <= 0 ||
            applicationProperties.getCircuitBreaker().getSlidingWindowSize() <= 0) {
            throw new IllegalStateException("Las propiedades de CircuitBreaker deben tener valores positivos");
        }
    }
}

record CircuitBreakerProperties(
        int failureRateThreshold,
        int waitDurationInOpenState,
        int slidingWindowSize
) {}

record ApplicationProperties(
        CircuitBreakerProperties circuitBreaker
) {}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: financial-comments-service
  datasource:
    url: jdbc:h2:mem:financial_comments_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

server:
  port: 8080
  servlet:
    context-path: /api

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always

resilience4j:
  circuitbreaker:
    instances:
      commentService:
        registerHealthIndicator: true
        failureRateThreshold: 50
        waitDurationInOpenState: 5000
        slidingWindowSize: 10

logging:
  level:
    root: INFO
    com.financial.comments: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE

// === ARCHIVO: src/main/java/com/financial/comments/domain/model/Comment.java ===
package com.financial.comments.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

public record Comment(
        @NotNull
        UUID id,

        @NotNull
        UUID productId,

        @NotBlank
        @Size(max = 255)
        String author,

        @NotBlank
        @Size(max = 2000)
        String content,

        @NotNull
        LocalDateTime createdAt,

        @NotNull
        LocalDateTime updatedAt,

        @NotNull
        CommentStatus status
) {
    public enum CommentStatus {
        PENDING, APPROVED, REJECTED, SPAM
    }

    public Comment {
        if (id == null) {
            throw new IllegalArgumentException("Comment ID cannot be null");
        }
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be blank");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be blank");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("CreatedAt cannot be null");
        }
        if (updatedAt == null) {
            throw new IllegalArgumentException("UpdatedAt cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        if (createdAt.isAfter(updatedAt)) {
            throw new IllegalArgumentException("CreatedAt cannot be after UpdatedAt");
        }
    }

    public Comment withStatus(CommentStatus newStatus) {
        return new Comment(
                this.id,
                this.productId,
                this.author,
                this.content,
                this.createdAt,
                LocalDateTime.now(),
                newStatus
        );
    }

    public Comment withContent(String newContent) {
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be blank");
        }
        return new Comment(
                this.id,
                this.productId,
                this.author,
                newContent,
                this.createdAt,
                LocalDateTime.now(),
                this.status
        );
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/domain/model/Product.java ===
package com.financial.comments.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record Product(
        @NotNull
        UUID id,

        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 500)
        String description,

        @NotNull
        ProductStatus status
) {
    public enum ProductStatus {
        ACTIVE, INACTIVE, DISCONTINUED
    }

    public Product {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be blank");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Product name cannot exceed 100 characters");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be blank");
        }
        if (description.length() > 500) {
            throw new IllegalArgumentException("Product description cannot exceed 500 characters");
        }
        if (status == null) {
            throw new IllegalArgumentException("Product status cannot be null");
        }
    }

    public boolean isActive() {
        return this.status == ProductStatus.ACTIVE;
    }

    public Product withName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be blank");
        }
        if (newName.length() > 100) {
            throw new IllegalArgumentException("Product name cannot exceed 100 characters");
        }
        return new Product(this.id, newName, this.description, this.status);
    }

    public Product withDescription(String newDescription) {
        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be blank");
        }
        if (newDescription.length() > 500) {
            throw new IllegalArgumentException("Product description cannot exceed 500 characters");
        }
        return new Product(this.id, this.name, newDescription, this.status);
    }

    public Product activate() {
        return new Product(this.id, this.name, this.description, ProductStatus.ACTIVE);
    }

    public Product deactivate() {
        return new Product(this.id, this.name, this.description, ProductStatus.INACTIVE);
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/domain/port/CommentRepository.java ===
package com.financial.comments.domain.port;

import com.financial.comments.domain.model.Comment;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(UUID id);
    Optional<Comment> findByProductIdAndAuthorAndContent(UUID productId, String author, String content);
    boolean existsByProductIdAndIdempotencyKey(UUID productId, String idempotencyKey);
}

// === ARCHIVO: src/main/java/com/financial/comments/domain/port/ProductRepository.java ===
package com.financial.comments.domain.port;

import com.financial.comments.domain.model.Product;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    
    Product save(Product product);
    
    Optional<Product> findById(UUID id);
    
    boolean existsById(UUID id);
    
    boolean existsByName(String name);
    
    void deleteById(UUID id);
}

// === ARCHIVO: src/main/java/com/financial/comments/domain/exception/OffensiveLanguageException.java ===
package com.financial.comments.domain.exception;

public class OffensiveLanguageException extends RuntimeException {
    
    private final String offensiveWord;
    private final String commentContent;
    
    public OffensiveLanguageException(String message) {
        super(message);
        this.offensiveWord = null;
        this.commentContent = null;
    }
    
    public OffensiveLanguageException(String message, String offensiveWord, String commentContent) {
        super(message);
        this.offensiveWord = offensiveWord;
        this.commentContent = commentContent;
    }
    
    public OffensiveLanguageException(String message, Throwable cause) {
        super(message, cause);
        this.offensiveWord = null;
        this.commentContent = null;
    }
    
    public OffensiveLanguageException(String message, String offensiveWord, String commentContent, Throwable cause) {
        super(message, cause);
        this.offensiveWord = offensiveWord;
        this.commentContent = commentContent;
    }
    
    public String getOffensiveWord() {
        return offensiveWord;
    }
    
    public String getCommentContent() {
        return commentContent;
    }
    
    public static OffensiveLanguageException withWord(String word, String content) {
        return new OffensiveLanguageException(
            "El comentario contiene lenguaje ofensivo: " + word,
            word,
            content
        );
    }
    
    public static OffensiveLanguageException withMultipleWords(String[] words, String content) {
        String wordList = String.join(", ", words);
        return new OffensiveLanguageException(
            "El comentario contiene lenguaje ofensivo: " + wordList,
            wordList,
            content
        );
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/domain/exception/DuplicateCommentException.java ===
package com.financial.comments.domain.exception;

import java.util.UUID;

public class DuplicateCommentException extends RuntimeException {
    
    private final UUID productId;
    private final String author;
    private final String idempotencyKey;
    
    public DuplicateCommentException(String message) {
        super(message);
        this.productId = null;
        this.author = null;
        this.idempotencyKey = null;
    }
    
    public DuplicateCommentException(String message, Throwable cause) {
        super(message, cause);
        this.productId = null;
        this.author = null;
        this.idempotencyKey = null;
    }
    
    public DuplicateCommentException(String message, UUID productId, String author) {
        super(message);
        this.productId = productId;
        this.author = author;
        this.idempotencyKey = null;
    }
    
    public DuplicateCommentException(String message, UUID productId, String author, String idempotencyKey) {
        super(message);
        this.productId = productId;
        this.author = author;
        this.idempotencyKey = idempotencyKey;
    }
    
    public DuplicateCommentException(String message, UUID productId, String author, String idempotencyKey, Throwable cause) {
        super(message, cause);
        this.productId = productId;
        this.author = author;
        this.idempotencyKey = idempotencyKey;
    }
    
    public UUID getProductId() {
        return productId;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIdempotencyKey() {
        return idempotencyKey;
    }
    
    public static DuplicateCommentException forProductAndAuthor(UUID productId, String author) {
        return new DuplicateCommentException(
            "Ya existe un comentario para el producto " + productId + " del autor " + author,
            productId,
            author
        );
    }
    
    public static DuplicateCommentException withIdempotencyKey(UUID productId, String idempotencyKey) {
        return new DuplicateCommentException(
            "Ya existe un comentario con la clave de idempotencia: " + idempotencyKey,
            productId,
            null,
            idempotencyKey
        );
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/domain/exception/ProductNotFoundException.java ===
package com.financial.comments.domain.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    private final UUID productId;

    public ProductNotFoundException(UUID productId) {
        super(String.format("Producto no encontrado con ID: %s", productId));
        this.productId = productId;
    }

    public ProductNotFoundException(UUID productId, String message) {
        super(message);
        this.productId = productId;
    }

    public ProductNotFoundException(UUID productId, Throwable cause) {
        super(String.format("Producto no encontrado con ID: %s", productId), cause);
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return "ProductNotFoundException{" +
                "productId=" + productId +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/infrastructure/adapter/CommentJpaRepository.java ===
package com.financial.comments.infrastructure.adapter;

import com.financial.comments.domain.model.Comment;
import com.financial.comments.domain.port.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, UUID>, CommentRepository {

    @Query("SELECT c FROM Comment c WHERE c.productId = :productId AND c.author = :author AND c.content = :content")
    Optional<Comment> findByProductIdAndAuthorAndContent(
            @Param("productId") UUID productId,
            @Param("author") String author,
            @Param("content") String content
    );

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Comment c WHERE c.productId = :productId AND c.idempotencyKey = :idempotencyKey")
    boolean existsByProductIdAndIdempotencyKey(
            @Param("productId") UUID productId,
            @Param("idempotencyKey") String idempotencyKey
    );

    @Override
    default Comment save(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("El comentario no puede ser nulo");
        }
        return saveComment(comment);
    }

    private Comment saveComment(Comment comment) {
        return save(comment);
    }

    @Override
    default Optional<Comment> findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del comentario no puede ser nulo");
        }
        return JpaRepository.super.findById(id);
    }

    @Override
    default Optional<Comment> findByProductIdAndAuthorAndContent(UUID productId, String author, String content) {
        if (productId == null || author == null || content == null) {
            throw new IllegalArgumentException("Los parámetros de búsqueda no pueden ser nulos");
        }
        return findByProductIdAndAuthorAndContent(productId, author, content);
    }

    @Override
    default boolean existsByProductIdAndIdempotencyKey(UUID productId, String idempotencyKey) {
        if (productId == null || idempotencyKey == null || idempotencyKey.isBlank()) {
            return false;
        }
        return existsByProductIdAndIdempotencyKey(productId, idempotencyKey);
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/infrastructure/adapter/ProductJpaRepository.java ===
package com.financial.comments.infrastructure.adapter;

import com.financial.comments.domain.model.Product;
import com.financial.comments.domain.port.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, UUID>, ProductRepository {

    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE' AND p.id = :id")
    Optional<Product> findActiveById(@Param("id") UUID id);

    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE'")
    List<Product> findAllActive();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.id = :id AND p.status = 'ACTIVE'")
    boolean existsActiveById(@Param("id") UUID id);

    @Override
    default Product save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        return saveProduct(product);
    }

    private Product saveProduct(Product product) {
        return save(product);
    }

    @Override
    default Optional<Product> findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }
        return JpaRepository.super.findById(id);
    }

    @Override
    default boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }
        return existsById(id);
    }

    @Override
    default boolean isActive(UUID id) {
        if (id == null) {
            return false;
        }
        return existsActiveById(id);
    }

    @Override
    default Optional<Product> findActiveProductById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }
        return findActiveById(id);
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/infrastructure/dto/CommentRequest.java ===
package com.financial.comments.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CommentRequest(
    @NotNull(message = "El ID del producto es obligatorio")
    UUID productId,

    @NotBlank(message = "El autor es obligatorio")
    @Size(min = 2, max = 100, message = "El autor debe tener entre 2 y 100 caracteres")
    String author,

    @NotBlank(message = "El contenido es obligatorio")
    @Size(min = 1, max = 1000, message = "El contenido debe tener entre 1 y 1000 caracteres")
    String content,

    @NotBlank(message = "La clave de idempotencia es obligatoria")
    @Size(max = 64, message = "La clave de idempotencia no puede exceder 64 caracteres")
    String idempotencyKey
) {}

// === ARCHIVO: src/main/java/com/financial/comments/infrastructure/dto/CommentResponse.java ===
package com.financial.comments.infrastructure.dto;

import com.financial.comments.domain.model.Comment;
import com.financial.comments.domain.model.Comment.CommentStatus;
import java.time.Instant;
import java.util.UUID;

public record CommentResponse(
    UUID id,
    UUID productId,
    String author,
    String content,
    CommentStatus status,
    Instant createdAt,
    String idempotencyKey
) {
    public static CommentResponse fromDomain(Comment comment) {
        return new CommentResponse(
            comment.id(),
            comment.productId(),
            comment.author(),
            comment.content(),
            comment.status(),
            comment.createdAt(),
            comment.idempotencyKey()
        );
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/application/usecase/CreateCommentUseCase.java ===
package com.financial.comments.application.usecase;

import com.financial.comments.application.validator.CommentValidator;
import com.financial.comments.domain.exception.DuplicateCommentException;
import com.financial.comments.domain.exception.OffensiveLanguageException;
import com.financial.comments.domain.exception.ProductNotFoundException;
import com.financial.comments.domain.model.Comment;
import com.financial.comments.domain.model.Comment.CommentStatus;
import com.financial.comments.domain.port.CommentRepository;
import com.financial.comments.domain.port.ProductRepository;
import com.financial.comments.infrastructure.dto.CommentRequest;
import com.financial.comments.infrastructure.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCommentUseCase {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final CommentValidator commentValidator;

    @Transactional
    public CommentResponse execute(CommentRequest request) {
        log.info("Iniciando creación de comentario para producto: {} con clave de idempotencia: {}",
            request.productId(), request.idempotencyKey());

        validateProductExists(request.productId());
        validateIdempotency(request.productId(), request.idempotencyKey());
        validateContent(request.content());

        Comment comment = buildComment(request);
        Comment savedComment = commentRepository.save(comment);

        log.info("Comentario creado exitosamente con ID: {}", savedComment.id());
        return CommentResponse.fromDomain(savedComment);
    }

    private void validateProductExists(UUID productId) {
        log.debug("Validando existencia del producto: {}", productId);
        if (!productRepository.existsById(productId)) {
            log.warn("Producto no encontrado: {}", productId);
            throw new ProductNotFoundException("Producto no encontrado con ID: " + productId);
        }
        log.debug("Producto validado exitosamente: {}", productId);
    }

    private void validateIdempotency(UUID productId, String idempotencyKey) {
        log.debug("Verificando clave de idempotencia: {} para producto: {}", idempotencyKey, productId);
        if (commentRepository.existsByProductIdAndIdempotencyKey(productId, idempotencyKey)) {
            log.warn("Detectado comentario duplicado con clave de idempotencia: {}", idempotencyKey);
            throw new DuplicateCommentException("Ya existe un comentario con esta clave de idempotencia");
        }
        log.debug("Clave de idempotencia verificada: {}", idempotencyKey);
    }

    private void validateContent(String content) {
        log.debug("Validando contenido del comentario");
        if (!commentValidator.isValid(content)) {
            log.warn("Contenido含有 lenguaje ofensivo detectado");
            throw new OffensiveLanguageException("El contenido del comentario contiene lenguaje ofensivo");
        }
        log.debug("Contenido validado exitosamente");
    }

    private Comment buildComment(CommentRequest request) {
        return new Comment(
            UUID.randomUUID(),
            request.productId(),
            request.author(),
            request.content(),
            CommentStatus.PENDING,
            Instant.now(),
            request.idempotencyKey()
        );
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/application/validator/CommentValidator.java ===
package com.financial.comments.application.validator;

import com.financial.comments.domain.exception.DuplicateCommentException;
import com.financial.comments.domain.exception.OffensiveLanguageException;
import com.financial.comments.domain.exception.ProductNotFoundException;
import com.financial.comments.domain.model.Product;
import com.financial.comments.domain.port.CommentRepository;
import com.financial.comments.domain.port.ProductRepository;
import com.financial.comments.infrastructure.dto.CommentRequest;
import com.financial.comments.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class CommentValidator {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[^a-zA-Z0-9\\s]");
    private static final Pattern WORD_BOUNDARY_PATTERN = Pattern.compile("\\b[a-zA-Z0-9]+\\b");

    public void validate(CommentRequest request) {
        validateOffensiveLanguage(request.content(), request.author());
        validateProductExists(request.productId());
        validateProductActive(request.productId());
        validateIdempotency(request.productId(), request.author(), request.content(), request.idempotencyKey());
    }

    private void validateOffensiveLanguage(String content, String author) {
        Set<String> offensiveWords = applicationProperties.getOffensiveWords();
        if (offensiveWords == null || offensiveWords.isEmpty()) {
            return;
        }

        String normalizedContent = content.toLowerCase().trim();
        String normalizedAuthor = author.toLowerCase().trim();

        List<String> contentWords = extractWords(normalizedContent);
        List<String> authorWords = extractWords(normalizedAuthor);

        for (String word : contentWords) {
            if (offensiveWords.contains(word)) {
                throw new OffensiveLanguageException(
                    "El contenido del comentario contiene lenguaje ofensivo: '" + word + "'"
                );
            }
        }

        for (String word : authorWords) {
            if (offensiveWords.contains(word)) {
                throw new OffensiveLanguageException(
                    "El nombre del autor contiene lenguaje ofensivo: '" + word + "'"
                );
            }
        }
    }

    private List<String> extractWords(String text) {
        return WORD_BOUNDARY_PATTERN.matcher(text)
            .results()
            .map(m -> m.group().toLowerCase())
            .toList();
    }

    private void validateProductExists(UUID productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(
                "Producto no encontrado con ID: " + productId
            ));
    }

    private void validateProductActive(UUID productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(
                "Producto no encontrado con ID: " + productId
            ));

        if (!product.isActive()) {
            throw new ProductNotFoundException(
                "El producto con ID " + productId + " no está activo"
            );
        }
    }

    private void validateIdempotency(UUID productId, String author, String content, String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return;
        }

        boolean exists = commentRepository.existsByProductIdAndIdempotencyKey(productId, idempotencyKey);
        if (exists) {
            throw new DuplicateCommentException(
                "Ya existe un comentario con la clave de idempotencia: " + idempotencyKey
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/infrastructure/config/GlobalExceptionHandler.java ===
package com.financial.comments.infrastructure.config;



import com.financial.comments.domain.model.Comment;
import com.financial.comments.domain.model.Product;
import com.financial.comments.domain.exception.DuplicateCommentException;
import com.financial.comments.domain.exception.OffensiveLanguageException;
import com.financial.comments.domain.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed");
        response.put("details", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFound(ProductNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Product Not Found");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(OffensiveLanguageException.class)
    public ResponseEntity<Map<String, Object>> handleOffensiveLanguage(OffensiveLanguageException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.put("error", "Offensive Language Detected");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(DuplicateCommentException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateComment(DuplicateCommentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", "Duplicate Comment");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Internal Server Error");
        response.put("message", "Ocurrió un error inesperado. Por favor, contacte al administrador.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

// === ARCHIVO: src/main/java/com/financial/comments/infrastructure/controller/CommentController.java ===
package com.financial.comments.infrastructure.controller;

import com.financial.comments.application.usecase.CreateCommentUseCase;
import com.financial.comments.domain.model.Comment;
import com.financial.comments.infrastructure.dto.CommentRequest;
import com.financial.comments.infrastructure.dto.CommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "Comentarios", description = "API para gestión de comentarios de productos financieros")
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping
    @Operation(summary = "Crear un nuevo comentario", 
               description = "Permite crear un nuevo comentario para un producto financiero específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Comentario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
        @ApiResponse(responseCode = "409", description = "Comentario duplicado"),
        @ApiResponse(responseCode = "422", description = "Contenido con lenguaje ofensivo"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest request) {
        Comment createdComment = createCommentUseCase.execute(request);
        CommentResponse response = mapToResponse(createdComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private CommentResponse mapToResponse(Comment comment) {
        return new CommentResponse(
            comment.id(),
            comment.productId(),
            comment.author(),
            comment.content(),
            comment.status().name(),
            comment.createdAt(),
            comment.idempotencyKey()
        );
    }
}

// === ARCHIVO: src/test/java/com/financial/comments/application/usecase/CreateCommentUseCaseTest.java ===
package com.financial.comments.application.usecase;

import com.financial.comments.domain.exception.DuplicateCommentException;
import com.financial.comments.domain.exception.OffensiveLanguageException;
import com.financial.comments.domain.exception.ProductNotFoundException;
import com.financial.comments.domain.model.Comment;
import com.financial.comments.domain.model.Comment.CommentStatus;
import com.financial.comments.domain.model.Product;
import com.financial.comments.domain.model.Product.ProductStatus;
import com.financial.comments.domain.port.CommentRepository;
import com.financial.comments.domain.port.ProductRepository;
import com.financial.comments.application.validator.CommentValidator;
import com.financial.comments.infrastructure.dto.CommentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateCommentUseCase - Tests")
class CreateCommentUseCaseTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CommentValidator commentValidator;

    private CreateCommentUseCase createCommentUseCase;

    @BeforeEach
    void setUp() {
        createCommentUseCase = new CreateCommentUseCase(
            commentRepository,
            productRepository,
            commentValidator
        );
    }

    @Nested
    @DisplayName("Escenarios de Éxito")
    class SuccessScenarios {

        @Test
        @DisplayName("Debería crear un comentario exitosamente cuando el producto existe y las validaciones pasan")
        void shouldCreateCommentSuccessfully() {
            UUID productId = UUID.randomUUID();
            String author = "usuario123";
            String content = "Excelente producto financiero";
            String idempotencyKey = UUID.randomUUID().toString();

            Product product = new Product(
                productId,
                "Cuenta de Ahorro",
                "Cuenta con alto rendimiento",
                ProductStatus.ACTIVE,
                new BigDecimal("1000.00"),
                LocalDateTime.now()
            );

            CommentRequest request = new CommentRequest(productId, author, content, idempotencyKey);

            when(productRepository.findById(productId)).thenReturn(Optional.of(product));
            when(commentRepository.existsByProductIdAndIdempotencyKey(productId, idempotencyKey))
                .thenReturn(false);
            when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> {
                Comment c = invocation.getArgument(0);
                return new Comment(
                    UUID.randomUUID(),
                    c.productId(),
                    c.author(),
                    c.content(),
                    CommentStatus.PENDING,
                    c.idempotencyKey(),
                    LocalDateTime.now()
                );
            });

            Comment result = createCommentUseCase.execute(request);

            assertThat(result).isNotNull();
            assertThat(result.productId()).isEqualTo(productId);
            assertThat(result.author()).isEqualTo(author);
            assertThat(result.content()).isEqualTo(content);
            assertThat(result.status()).isEqualTo(CommentStatus.PENDING);

            verify(commentValidator).validate(content);
            verify(commentRepository).save(any(Comment.class));
        }

        @Test
        @DisplayName("Debería rechazar comentario duplicado usando clave de idempotencia")
        void shouldRejectDuplicateCommentWithIdempotencyKey() {
            UUID productId = UUID.randomUUID();
            String idempotencyKey = "duplicate-key-123";
            CommentRequest request = new CommentRequest(
                productId,
                "usuario",
                "Contenido del comentario",
                idempotencyKey
            );

            when(commentRepository.existsByProductIdAndIdempotencyKey(productId, idempotencyKey))
                .thenReturn(true);

            assertThatThrownBy(() -> createCommentUseCase.execute(request))
                .isInstanceOf(DuplicateCommentException.class)
                .hasMessageContaining("Ya existe un comentario con esta clave de idempotencia");

            verify(commentRepository, never()).save(any(Comment.class));
        }
    }

    @Nested
    @DisplayName("Escenarios de Error - Producto")
    class ProductErrorScenarios {

        @Test
        @DisplayName("Debería lanzar ProductNotFoundException cuando el producto no existe")
        void shouldThrowExceptionWhenProductNotFound() {
            UUID productId = UUID.randomUUID();
            CommentRequest request = new CommentRequest(
                productId,
                "usuario",
                "Comentario",
                UUID.randomUUID().toString()
            );

            when(productRepository.findById(productId)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> createCommentUseCase.execute(request))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("Producto no encontrado");

            verify(commentRepository, never()).save(any(Comment.class));
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el producto está inactivo")
        void shouldThrowExceptionWhenProductIsInactive() {
            UUID productId = UUID.randomUUID();
            Product inactiveProduct = new Product(
                productId,
                "Producto Inactivo",
                "Descripción",
                ProductStatus.INACTIVE,
                new BigDecimal("500.00"),
                LocalDateTime.now()
            );

            CommentRequest request = new CommentRequest(
                productId,
                "usuario",
                "Comentario",
                UUID.randomUUID().toString()
            );

            when(productRepository.findById(productId)).thenReturn(Optional.of(inactiveProduct));

            assertThatThrownBy(() -> createCommentUseCase.execute(request))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("inactivo");

            verify(commentRepository, never()).save(any(Comment.class));
        }
    }

    @Nested
    @DisplayName("Escenarios de Error - Validación")
    class ValidationErrorScenarios {

        @Test
        @DisplayName("Debería lanzar OffensiveLanguageException cuando el contenido tiene lenguaje ofensivo")
        void shouldThrowExceptionForOffensiveLanguage() {
            UUID productId = UUID.randomUUID();
            String offensiveContent = "Este producto es una mierda";
            Product product = new Product(
                productId,
                "Producto",
                "Descripción",
                ProductStatus.ACTIVE,
                new BigDecimal("100.00"),
                LocalDateTime.now()
            );

            CommentRequest request = new CommentRequest(
                productId,
                "usuario",
                offensiveContent,
                UUID.randomUUID().toString()
            );

            when(productRepository.findById(productId)).thenReturn(Optional.of(product));
            when(commentRepository.existsByProductIdAndIdempotencyKey(any(), any()))
                .thenReturn(false);
            doThrow(new OffensiveLanguageException("Lenguaje ofensivo detectado"))
                .when(commentValidator).validate(offensiveContent);

            assertThatThrownBy(() -> createCommentUseCase.execute(request))
                .isInstanceOf(OffensiveLanguageException.class)
                .hasMessageContaining("Lenguaje ofensivo");

            verify(commentRepository, never()).save(any(Comment.class));
        }
    }

    @Nested
    @DisplayName("Pruebas de Comportamiento")
    class BehaviorTests {

        @Test
        @DisplayName("Debería guardar comentario con estado PENDING por defecto")
        void shouldSaveCommentWithPendingStatusByDefault() {
            UUID productId = UUID.randomUUID();
            CommentRequest request = new CommentRequest(
                productId,
                "autor",
                "Contenido válido",
                "key-123"
            );

            Product product = new Product(
                productId,
                "Producto",
                "Descripción",
                ProductStatus.ACTIVE,
                new BigDecimal("200.00"),
                LocalDateTime.now()
            );

            ArgumentCaptor<Comment> commentCaptor = ArgumentCaptor.forClass(Comment.class);

            when(productRepository.findById(productId)).thenReturn(Optional.of(product));
            when(commentRepository.existsByProductIdAndIdempotencyKey(productId, "key-123"))
                .thenReturn(false);
            when(commentRepository.save(commentCaptor.capture())).thenAnswer(i -> i.getArgument(0));

            createCommentUseCase.execute(request);

            Comment savedComment = commentCaptor.getValue();
            assertThat(savedComment.status()).isEqualTo(CommentStatus.PENDING);
        }

        @Test
        @DisplayName("Debería propagar excepción Runtime del validador")
        void shouldPropagateRuntimeExceptionFromValidator() {
            UUID productId = UUID.randomUUID();
            String content = "contenido";

            CommentRequest request = new CommentRequest(
                productId,
                "autor",
                content,
                "key"
            );

            Product product = new Product(
                productId,
                "Producto",
                "Descripción",
                ProductStatus.ACTIVE,
                new BigDecimal("100.00"),
                LocalDateTime.now()
            );

            when(productRepository.findById(productId)).thenReturn(Optional.of(product));
            when(commentRepository.existsByProductIdAndIdempotencyKey(any(), any()))
                .thenReturn(false);
            doThrow(new RuntimeException("Error de validación inesperado"))
                .when(commentValidator).validate(content);

            assertThatThrownBy(() -> createCommentUseCase.execute(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Error de validación inesperado");
        }
    }
}

// === ARCHIVO: src/test/java/com/financial/comments/infrastructure/controller/CommentControllerTest.java ===
package com.financial.comments.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.comments.application.usecase.CreateCommentUseCase;
import com.financial.comments.domain.exception.DuplicateCommentException;
import com.financial.comments.domain.exception.OffensiveLanguageException;
import com.financial.comments.domain.exception.ProductNotFoundException;
import com.financial.comments.domain.model.Comment;
import com.financial.comments.domain.model.Comment.CommentStatus;
import com.financial.comments.infrastructure.dto.CommentRequest;
import com.financial.comments.infrastructure.dto.CommentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@DisplayName("CommentController - Integration Tests")
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateCommentUseCase createCommentUseCase;

    private CommentRequest validRequest;
    private CommentResponse successResponse;

    @BeforeEach
    void setUp() {
        UUID productId = UUID.randomUUID();
        String idempotencyKey = UUID.randomUUID().toString();

        validRequest = new CommentRequest(
            productId,
            "usuario_ejemplo",
            "Este producto me parece excelente para mis inversiones",
            idempotencyKey
        );

        successResponse = new CommentResponse(
            UUID.randomUUID(),
            productId,
            "usuario_ejemplo",
            "Este producto me parece excelente para mis inversiones",
            CommentStatus.PENDING.toString(),
            idempotencyKey,
            LocalDateTime.now().toString()
        );
    }

    @Nested
    @DisplayName("POST /api/comments - Casos de Éxito")
    class CreateCommentSuccess {

        @Test
        @DisplayName("Debería retornar 201 Created cuando el comentario se crea exitosamente")
        void shouldReturn201WhenCommentCreated() throws Exception {
            when(createCommentUseCase.execute(any(CommentRequest.class)))
                .thenReturn(Comment.fromCommentResponse(successResponse));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.productId").value(validRequest.productId().toString()))
                .andExpect(jsonPath("$.author").value(validRequest.author()))
                .andExpect(jsonPath("$.content").value(validRequest.content()))
                .andExpect(jsonPath("$.status").value("PENDING"));
        }

        @Test
        @DisplayName("Debería crear comentario con contenido válido y retornar detalles correctos")
        void shouldCreateCommentWithValidContent() throws Exception {
            UUID commentId = UUID.randomUUID();
            CommentResponse response = new CommentResponse(
                commentId,
                validRequest.productId(),
                validRequest.author(),
                validRequest.content(),
                "PENDING",
                validRequest.idempotencyKey(),
                LocalDateTime.now().toString()
            );

            when(createCommentUseCase.execute(any(CommentRequest.class)))
                .thenReturn(Comment.fromCommentResponse(response));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(commentId.toString()))
                .andExpect(jsonPath("$.idempotencyKey").value(validRequest.idempotencyKey()));
        }
    }

    @Nested
    @DisplayName("POST /api/comments - Casos de Error")
    class CreateCommentErrors {

        @Test
        @DisplayName("Debería retornar 404 cuando el producto no existe")
        void shouldReturn404WhenProductNotFound() throws Exception {
            when(createCommentUseCase.execute(any(CommentRequest.class)))
                .thenThrow(new ProductNotFoundException("Producto no encontrado con ID: " + validRequest.productId()));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.message").exists());
        }

        @Test
        @DisplayName("Debería retornar 409 cuando hay comentario duplicado")
        void shouldReturn409WhenDuplicateComment() throws Exception {
            when(createCommentUseCase.execute(any(CommentRequest.class)))
                .thenThrow(new DuplicateCommentException("Ya existe un comentario con esta clave de idempotencia"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("CONFLICT"))
                .andExpect(jsonPath("$.message").exists());
        }

        @Test
        @DisplayName("Debería retornar 422 cuando se detecta lenguaje ofensivo")
        void shouldReturn422WhenOffensiveLanguage() throws Exception {
            when(createCommentUseCase.execute(any(CommentRequest.class)))
                .thenThrow(new OffensiveLanguageException("El contenido contiene lenguaje ofensivo"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.error").value("UNPROCESSABLE_ENTITY"))
                .andExpect(jsonPath("$.message").exists());
        }
    }

    @Nested
    @DisplayName("POST /api/comments - Validaciones de Request")
    class RequestValidation {

        @Test
        @DisplayName("Debería retornar 400 cuando el request tiene productId nulo")
        void shouldReturn400WhenProductIdIsNull() throws Exception {
            CommentRequest invalidRequest = new CommentRequest(
                null,
                "autor",
                "Contenido",
                "key"
            );

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
        }

        @Test
        @DisplayName("Debería retornar 400 cuando el author está vacío")
        void shouldReturn400WhenAuthorIsBlank() throws Exception {
            CommentRequest invalidRequest = new CommentRequest(
                UUID.randomUUID(),
                "",
                "Contenido",
                "key"
            );

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
        }

        @Test
        @DisplayName("Debería retornar 400 cuando el content excede el límite máximo")
        void shouldReturn400WhenContentExceedsMaxLength() throws Exception {
            String longContent = "A".repeat(1001);
            CommentRequest invalidRequest = new CommentRequest(
                UUID.randomUUID(),
                "autor",
                longContent,
                "key"
            );

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
        }

        @Test
        @DisplayName("Debería retornar 400 cuando el request está vacío")
        void shouldReturn400WhenRequestBodyIsEmpty() throws Exception {
            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{}"))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debería retornar 415 cuando el content-type es incorrecto")
        void shouldReturn415WhenContentTypeIsIncorrect() throws Exception {
            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.TEXT_PLAIN)
                    .content("invalid content"))
                .andExpect(status().isUnsupportedMediaType());
        }
    }

    @Nested
    @DisplayName("Pruebas de Encabezados y Configuración")
    class HeaderTests {

        @Test
        @DisplayName("Debería incluir headers de tipo de contenido en la respuesta")
        void shouldIncludeContentTypeHeaders() throws Exception {
            when(createCommentUseCase.execute(any(CommentRequest.class)))
                .thenReturn(Comment.fromCommentResponse(successResponse));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
    }
}
```

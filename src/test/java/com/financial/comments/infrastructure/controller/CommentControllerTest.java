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
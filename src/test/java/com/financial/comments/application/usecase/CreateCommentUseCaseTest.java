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
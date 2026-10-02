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
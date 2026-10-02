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
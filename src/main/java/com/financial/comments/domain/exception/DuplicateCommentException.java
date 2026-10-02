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
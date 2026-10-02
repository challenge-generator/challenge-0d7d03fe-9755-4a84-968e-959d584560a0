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
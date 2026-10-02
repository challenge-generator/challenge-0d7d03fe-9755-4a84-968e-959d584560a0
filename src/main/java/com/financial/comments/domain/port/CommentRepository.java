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
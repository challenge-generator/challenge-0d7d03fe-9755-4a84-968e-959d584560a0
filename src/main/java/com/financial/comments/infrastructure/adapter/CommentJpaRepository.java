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
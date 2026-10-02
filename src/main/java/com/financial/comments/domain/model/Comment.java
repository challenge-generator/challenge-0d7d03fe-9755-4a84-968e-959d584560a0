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
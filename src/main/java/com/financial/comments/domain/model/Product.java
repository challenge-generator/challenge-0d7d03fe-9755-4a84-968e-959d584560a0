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
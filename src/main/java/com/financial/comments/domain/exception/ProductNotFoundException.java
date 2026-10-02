package com.financial.comments.domain.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    private final UUID productId;

    public ProductNotFoundException(UUID productId) {
        super(String.format("Producto no encontrado con ID: %s", productId));
        this.productId = productId;
    }

    public ProductNotFoundException(UUID productId, String message) {
        super(message);
        this.productId = productId;
    }

    public ProductNotFoundException(UUID productId, Throwable cause) {
        super(String.format("Producto no encontrado con ID: %s", productId), cause);
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return "ProductNotFoundException{" +
                "productId=" + productId +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
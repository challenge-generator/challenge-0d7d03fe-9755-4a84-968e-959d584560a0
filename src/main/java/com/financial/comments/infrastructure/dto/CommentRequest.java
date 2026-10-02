package com.financial.comments.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CommentRequest(
    @NotNull(message = "El ID del producto es obligatorio")
    UUID productId,

    @NotBlank(message = "El autor es obligatorio")
    @Size(min = 2, max = 100, message = "El autor debe tener entre 2 y 100 caracteres")
    String author,

    @NotBlank(message = "El contenido es obligatorio")
    @Size(min = 1, max = 1000, message = "El contenido debe tener entre 1 y 1000 caracteres")
    String content,

    @NotBlank(message = "La clave de idempotencia es obligatoria")
    @Size(max = 64, message = "La clave de idempotencia no puede exceder 64 caracteres")
    String idempotencyKey
) {}
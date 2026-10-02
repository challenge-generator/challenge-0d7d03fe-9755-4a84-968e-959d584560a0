package com.financial.comments.infrastructure.controller;

import com.financial.comments.application.usecase.CreateCommentUseCase;
import com.financial.comments.domain.model.Comment;
import com.financial.comments.infrastructure.dto.CommentRequest;
import com.financial.comments.infrastructure.dto.CommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "Comentarios", description = "API para gestión de comentarios de productos financieros")
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping
    @Operation(summary = "Crear un nuevo comentario", 
               description = "Permite crear un nuevo comentario para un producto financiero específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Comentario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
        @ApiResponse(responseCode = "409", description = "Comentario duplicado"),
        @ApiResponse(responseCode = "422", description = "Contenido con lenguaje ofensivo"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest request) {
        Comment createdComment = createCommentUseCase.execute(request);
        CommentResponse response = mapToResponse(createdComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private CommentResponse mapToResponse(Comment comment) {
        return new CommentResponse(
            comment.id(),
            comment.productId(),
            comment.author(),
            comment.content(),
            comment.status().name(),
            comment.createdAt(),
            comment.idempotencyKey()
        );
    }
}
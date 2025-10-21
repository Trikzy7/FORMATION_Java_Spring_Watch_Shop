package com.example.tp_cbtw_java_watch_shop.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.*;

@ControllerAdvice
public class RestExceptionHandler {

    // Structure d'erreur standard
    public record ApiError(String timestamp, int status, String error, String message, String path, Map<String,String> fieldErrors) {}

    // Validation @Valid errors (DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fe.getField(), fe.getDefaultMessage());
        }
        ApiError body = new ApiError(Instant.now().toString(), HttpStatus.BAD_REQUEST.value(),
                "Bad Request", "Erreur de validation des champs", request.getDescription(false).replace("uri=", ""), fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // ConstraintViolationException (ex : validation sur @RequestParam) :
    //    Exemple d'utilisation dans un contrôleur :
            //    @GetMapping("/watch")
            //    public Watch getWatchById(@RequestParam @Min(1) Long id) { ... }
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    protected ResponseEntity<ApiError> handleConstraintViolation(jakarta.validation.ConstraintViolationException ex, WebRequest request) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(v -> {
            String path = v.getPropertyPath().toString();
            fieldErrors.put(path, v.getMessage());
        });
        ApiError body = new ApiError(Instant.now().toString(), HttpStatus.BAD_REQUEST.value(),
                "Bad Request", "Paramètre(s) invalide(s)", request.getDescription(false).replace("uri=", ""), fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // Accès refusé
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        ApiError body = new ApiError(Instant.now().toString(), HttpStatus.FORBIDDEN.value(),
                "Forbidden", ex.getMessage(), request.getDescription(false).replace("uri=", ""), Collections.emptyMap());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    // Exceptions métier / runtime
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ApiError> handleRuntime(RuntimeException ex, WebRequest request) {
        ApiError body = new ApiError(Instant.now().toString(), HttpStatus.BAD_REQUEST.value(),
                "Error", ex.getMessage(), request.getDescription(false).replace("uri=", ""), Collections.emptyMap());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // Fallback
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handleAll(Exception ex, WebRequest request) {
        ApiError body = new ApiError(Instant.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error", "Une erreur inattendue est survenue", request.getDescription(false).replace("uri=", ""), Collections.emptyMap());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
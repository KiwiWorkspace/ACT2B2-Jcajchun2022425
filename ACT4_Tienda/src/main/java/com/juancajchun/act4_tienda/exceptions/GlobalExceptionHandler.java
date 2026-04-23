package com.juancajchun.act4_tienda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {

        // Si TODOS los campos vienen nulos/vacíos, significa que mandaron {} o un body sin datos
        boolean bodyVacio = ex.getBindingResult().getFieldErrors()
                .stream()
                .allMatch(e -> e.getRejectedValue() == null
                        || e.getRejectedValue().toString().isBlank());

        if (bodyVacio) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "El body del JSON no debe estar vacío.");
            error.put("descripcion", "Debe enviar los campos: nombreProducto, precioProducto, stockProducto.");
            return ResponseEntity.badRequest().body(error);
        }

        // Si solo algunos campos fallan, muestra cuáles son
        Map<String, Object> errores = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errores.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonParse(HttpMessageNotReadableException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Estructura del JSON incorrecta o tipo de dato inválido.");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourcesNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Ocurrió un error interno en el servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
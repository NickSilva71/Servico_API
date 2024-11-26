package com.projeto.cadastro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FuncionarioNotFoundException.class)
    public ResponseEntity<ApiError> handleFuncionarioNotFoundException(FuncionarioNotFoundException ex){
        ApiError apiError = new ApiError();
        apiError.setCode("Servico-001");
        apiError.setStatus(404);
        apiError.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
package com.exercicio.apolice.exception.handler;


import com.exercicio.apolice.exception.CadastroException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {
    private static final String mensagem = "(exceção): (mensagem).";


    private String formatarMensagem(Exception e) {
        return mensagem
                .replace("(mensagem)", e.getMessage())
                .replace("(exceção)", e.getClass().getSimpleName());
    }

    @ExceptionHandler(CadastroException.class)
    public ResponseEntity<Object> handleCadastroException(CadastroException ce) {
        return ResponseEntity.badRequest().body(formatarMensagem(ce));
    }
}

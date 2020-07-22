package com.exercicio.apolice.exception.handler;

import com.exercicio.apolice.exception.*;
import org.springframework.http.HttpStatus;
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


    @ExceptionHandler(ClienteInativoException.class)
    public ResponseEntity<Object> handleClienteInativoException(ClienteInativoException cie) {
        return ResponseEntity.badRequest().body(formatarMensagem(cie));
    }


    @ExceptionHandler(ClienteInexistenteException.class)
    public ResponseEntity<Object> handleClienteInexistenteException(ClienteInexistenteException cie) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(formatarMensagem(cie));
    }


    @ExceptionHandler(PagamentoException.class)
    public ResponseEntity<Object> handlePagamentoException(PagamentoException pe) {
        return ResponseEntity.badRequest().body(formatarMensagem(pe));
    }


    @ExceptionHandler(PagamentoInexistenteException.class)
    public ResponseEntity<Object> handlePagamentoInexistenteException(PagamentoInexistenteException pie) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(formatarMensagem(pie));
    }
}

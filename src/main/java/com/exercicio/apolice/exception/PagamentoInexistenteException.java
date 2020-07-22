package com.exercicio.apolice.exception;

public class PagamentoInexistenteException extends RuntimeException {
    public PagamentoInexistenteException(String errorMessage) {
        super(errorMessage);
    }
}
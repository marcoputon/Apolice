package com.exercicio.apolice.exception;

public class PagamentoException extends RuntimeException {
    public PagamentoException(String errorMessage) {
        super(errorMessage);
    }
}
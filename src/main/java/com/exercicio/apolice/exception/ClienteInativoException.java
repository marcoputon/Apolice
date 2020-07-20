package com.exercicio.apolice.exception;

public class ClienteInativoException extends RuntimeException {
    public ClienteInativoException(String errorMessage) {
        super(errorMessage);
    }
}
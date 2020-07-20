package com.exercicio.apolice.exception;

public class ClienteInexistenteException extends RuntimeException {
    public ClienteInexistenteException(String errorMessage) {
        super(errorMessage);
    }
}

package com.exercicio.apolice.exception;

public class CadastroException extends RuntimeException {
    public CadastroException(String errorMessage) {
        super(errorMessage);
    }
}

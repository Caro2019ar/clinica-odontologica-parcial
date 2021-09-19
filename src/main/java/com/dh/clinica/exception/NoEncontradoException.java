package com.dh.clinica.exception;

public class NoEncontradoException extends RuntimeException{

    public NoEncontradoException(String message) {
        super(message);
    }

    public NoEncontradoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}


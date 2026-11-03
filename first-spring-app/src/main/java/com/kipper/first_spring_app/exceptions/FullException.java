package com.kipper.first_spring_app.exceptions;

public class FullException extends RuntimeException {

    public FullException() {
        super("Erro interno do servidor");
    }

    public FullException(String message) {
        super(message);
    }

}

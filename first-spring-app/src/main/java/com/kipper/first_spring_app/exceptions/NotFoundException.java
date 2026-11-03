package com.kipper.first_spring_app.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Recurso não encontrado");
    }

    public NotFoundException(String message) {
        super(message);
    }
}

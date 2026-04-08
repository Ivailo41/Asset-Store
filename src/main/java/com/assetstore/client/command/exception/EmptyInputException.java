package com.assetstore.client.command.exception;

public class EmptyInputException extends RuntimeException {
    public EmptyInputException(String message) {
        super(message);
    }
}

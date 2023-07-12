package com.dynamicthrottle.demo.exception;

public class CommandFailedException extends RuntimeException {
    public CommandFailedException(String message) {
        super(message);
    }
}

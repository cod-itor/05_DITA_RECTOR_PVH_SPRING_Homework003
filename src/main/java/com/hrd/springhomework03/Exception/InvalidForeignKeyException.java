package com.hrd.springhomework03.Exception;

public class InvalidForeignKeyException extends RuntimeException {
    public InvalidForeignKeyException(String message) {
        super(message);
    }
}

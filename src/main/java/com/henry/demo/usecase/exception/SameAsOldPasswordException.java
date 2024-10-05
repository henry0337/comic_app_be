package com.henry.demo.usecase.exception;

public class SameAsOldPasswordException extends RuntimeException {
    public SameAsOldPasswordException(String message) {
        super(message);
    }
}
package com.henry.demo.exceptions;

public class SameAsOldPasswordException extends RuntimeException {
    public SameAsOldPasswordException(String message) {
        super(message);
    }
}
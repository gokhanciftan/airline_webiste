package org.example.havayolu.exception;

public class WrongPassword extends RuntimeException {
    public WrongPassword(String wrongPassword) {
        super(wrongPassword);
    }

}

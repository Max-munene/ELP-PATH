package com.example.emtechelppathbackend.exceptions;

public class UserDetailsNotFoundException extends RuntimeException{
    private String message;
    public UserDetailsNotFoundException(String message){
        super(message);
        this.message = message;
    }
}

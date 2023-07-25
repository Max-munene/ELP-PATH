package com.example.emtechelppathbackend.customexceptions;

public class ScholarEducationNotFoundException extends RuntimeException {
    public ScholarEducationNotFoundException(String message) {
        super(message);
    }
}


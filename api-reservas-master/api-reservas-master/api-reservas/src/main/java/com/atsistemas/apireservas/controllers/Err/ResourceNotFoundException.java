package com.atsistemas.apireservas.controllers.Err;

public class ResourceNotFoundException extends RuntimeException{
    private static String customMessage = "Resource with id '%s' not found";
    public ResourceNotFoundException(Integer id) {
        super(String.format(customMessage, id));
    }
}

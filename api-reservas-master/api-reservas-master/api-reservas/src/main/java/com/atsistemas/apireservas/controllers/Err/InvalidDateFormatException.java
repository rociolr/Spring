package com.atsistemas.apireservas.controllers.Err;

public class InvalidDateFormatException extends RuntimeException {
    private static String defaultMessage = "Date '%s' could not be parsed because it´s format is incorrect";

    public InvalidDateFormatException(String parsedDate) {
        super(String.format(defaultMessage, parsedDate));
    }
}

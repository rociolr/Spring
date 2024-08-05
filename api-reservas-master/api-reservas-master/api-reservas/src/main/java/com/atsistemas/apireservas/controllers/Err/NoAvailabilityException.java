package com.atsistemas.apireservas.controllers.Err;

import java.time.LocalDate;

public class NoAvailabilityException extends RuntimeException {
    private static String customMessage = "There isn´t any availability entry for date %s or the number of available rooms is 0";

    public NoAvailabilityException(LocalDate date) {
        super(String.format(customMessage, date));
    }
}

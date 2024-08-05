package com.atsistemas.apireservas.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {
    static String defaultDatePattern = "dd/MM/yyyy";
    static  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultDatePattern);

    public static LocalDate getLocalDateFromString(String stringDate) throws DateTimeParseException {
        return LocalDate.parse(stringDate, formatter);
    }

    public static List<LocalDate> getDatesBetweenTwoDates(LocalDate dateFrom, LocalDate dateTo) {
        List<LocalDate> datesList = new ArrayList<>();
        while (!dateFrom.isAfter(dateTo)) {
            datesList.add(dateFrom);
            dateFrom = dateFrom.plusDays(1);
        }
        return datesList;
    }
}

package com.atsistemas.apireservas.services;

import com.atsistemas.apireservas.entities.Availability;
import com.atsistemas.apireservas.utilities.filters.AvailabilitiesFilter;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilitiesService {
    void openAvailability(Integer idHotel, Integer nHabitaciones, LocalDate dateFrom, LocalDate dateTo);
    void reduceAvailability(Integer idHotel, LocalDate dateFrom, LocalDate dateTo);
    List<Availability> consultAvailability(AvailabilitiesFilter availabilitiesFilter);




}

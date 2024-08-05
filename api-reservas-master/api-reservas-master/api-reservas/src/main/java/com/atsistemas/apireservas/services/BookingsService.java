package com.atsistemas.apireservas.services;

import com.atsistemas.apireservas.entities.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingsService {
    void saveBooking(Booking booking);

    List<Booking> findBookingsForHotelBetweenDates(Integer idHotel, LocalDate dateFrom, LocalDate dateTo);

    Optional<Booking> findBookingById (Integer bookId);

    void cancelBooking(Integer bookId);
    List<Booking> findBookingfordatefrom(LocalDate dateFrom);
}

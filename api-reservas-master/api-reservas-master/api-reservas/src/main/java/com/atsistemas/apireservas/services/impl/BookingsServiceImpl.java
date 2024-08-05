package com.atsistemas.apireservas.services.impl;

import com.atsistemas.apireservas.entities.Booking;
import com.atsistemas.apireservas.repositories.BookingsRepository;
import com.atsistemas.apireservas.services.AvailabilitiesService;
import com.atsistemas.apireservas.services.BookingsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingsServiceImpl implements BookingsService {

    private BookingsRepository bookingsRepository;
    private AvailabilitiesService availabilitiesService;


    public BookingsServiceImpl(BookingsRepository bookingsRepository, AvailabilitiesService availabilitiesService) {
        this.bookingsRepository = bookingsRepository;
        this.availabilitiesService = availabilitiesService;
    }

    @Override
    public void saveBooking(Booking booking) {
        availabilitiesService.reduceAvailability(booking.getIdHotel(), booking.getDateFrom(), booking.getDateTo());
        bookingsRepository.save(booking);
    }

    @Override
    public List<Booking> findBookingsForHotelBetweenDates(Integer idHotel, LocalDate dateFrom, LocalDate dateTo) {
        return bookingsRepository.findBookingssForHotelBetweenDates(idHotel, dateFrom, dateTo);
    }
public List<Booking> findBookingfordatefrom(LocalDate dateFrom){
   return     bookingsRepository.findByDateFrom(dateFrom);

}
    @Override
    public Optional<Booking> findBookingById(Integer bookId) {
        return bookingsRepository.findById(bookId);
    }

    @Override
    public void cancelBooking(Integer bookId) {
        Optional<Booking> booking = findBookingById(bookId);
        if (booking.isPresent()) {
            availabilitiesService.openAvailability(booking.get().getIdHotel(), 1, booking.get().getDateFrom(),
                    booking.get().getDateTo());
            bookingsRepository.deleteById(bookId);
        }

    }
}

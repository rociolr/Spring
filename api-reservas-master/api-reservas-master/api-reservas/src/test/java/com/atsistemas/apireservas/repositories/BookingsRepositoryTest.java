package com.atsistemas.apireservas.repositories;

import com.atsistemas.apireservas.entities.Booking;
import com.atsistemas.apireservas.utilities.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BookingsRepositoryTest {
    @Autowired
    private BookingsRepository bookingsRepository;

    @Test
    public void testFindAll() {
        List<Booking> bookingList = bookingsRepository.findAll();
        Assertions.assertEquals(1, bookingList.size());
    }

    @Test
    public void testFindById() {
        Optional<Booking> booking = bookingsRepository.findById(1);
        Assertions.assertTrue(booking.isPresent());
        Assertions.assertEquals(booking.get().getEmail(), "juanperez@gmail.com");
    }

    @Test
    public void testFindByIdNotFound() {
        Optional<Booking> booking = bookingsRepository.findById(10);
        Assertions.assertFalse(booking.isPresent());
    }


    @Test
    @Transactional
    public void testSave() {
        String email = "alvaro@gmail.com";
        Booking booking = new Booking(1, 1, DateUtils.getLocalDateFromString("25/03/2023"),
                DateUtils.getLocalDateFromString("25/03/2023"), email);
        Booking savedBooking = bookingsRepository.save(booking);
        Assertions.assertNotNull(savedBooking);
        Assertions.assertEquals(email, savedBooking.getEmail());
    }

    @Test
    @Transactional
    //DELETE COMO LO BORRA: ENTONCES COMPRUEBA Q NO ESTE PRESENTE
    public void testDelete() {
        Integer id = 1;
        bookingsRepository.deleteById(id);
        Assertions.assertFalse(bookingsRepository.findById(id).isPresent());
    }

    @Test
    public void testFindBookingssForHotelBetweenDates() {
        Integer idHotel = 2;
        LocalDate dateFrom = DateUtils.getLocalDateFromString("20/03/2023");
        LocalDate dateTo = DateUtils.getLocalDateFromString("26/03/2023");
        List<Booking> bookingList = bookingsRepository.findBookingssForHotelBetweenDates(idHotel,  dateFrom, dateTo);
        Assertions.assertEquals(4, bookingList.size());
    }
}

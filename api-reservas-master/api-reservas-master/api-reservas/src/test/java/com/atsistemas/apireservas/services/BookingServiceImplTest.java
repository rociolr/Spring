package com.atsistemas.apireservas.services;

import com.atsistemas.apireservas.entities.Booking;
import com.atsistemas.apireservas.repositories.BookingsRepository;
import com.atsistemas.apireservas.services.impl.AvailabilitiesServiceImpl;
import com.atsistemas.apireservas.services.impl.BookingsServiceImpl;
import com.atsistemas.apireservas.utilities.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {
    private BookingsServiceImpl bookingsService;
    @Mock
    private BookingsRepository bookingsRepository;
    @Mock
    private AvailabilitiesServiceImpl availabilitiesService;

    @BeforeEach
    public void initialize() {
        this.bookingsService = new BookingsServiceImpl(bookingsRepository, availabilitiesService);
    }

    @Test
    public void testfindBookingsForHotelBetweenDates() {
        List<Booking> bookingListMock = List.of(
                new Booking(1, 1, DateUtils.getLocalDateFromString("20/03/2023"),
                        DateUtils.getLocalDateFromString("25/03/2023"), "juan@Gmail.com"),
                new Booking(2, 1, DateUtils.getLocalDateFromString("24/03/2023"),
                        DateUtils.getLocalDateFromString("29/03/2023"), "pepe@Gmail.com"),
                new Booking(3, 1, DateUtils.getLocalDateFromString("27/03/2023"),
                        DateUtils.getLocalDateFromString("30/03/2023"), "lola@Gmail.com")
        );
        LocalDate dateFrom = DateUtils.getLocalDateFromString("20/03/2023");
        LocalDate dateTo = DateUtils.getLocalDateFromString("30/03/2023");
        Integer idHotel = 1;
        Mockito.when(bookingsRepository.findBookingssForHotelBetweenDates(idHotel, dateFrom, dateTo))
                .thenReturn(bookingListMock);
        List<Booking> bookingList = bookingsService.findBookingsForHotelBetweenDates(idHotel, dateFrom, dateTo);
        Assertions.assertEquals(bookingListMock.size(), bookingList.size());
        Mockito.verify(bookingsRepository, Mockito.times(1))
                .findBookingssForHotelBetweenDates(idHotel, dateFrom, dateTo);

    }

    @Test
    public void testfindBookingsForHotelBetweenDatesNull() {
        List<Booking> bookingListMock = new ArrayList<>();
        LocalDate dateFrom = DateUtils.getLocalDateFromString("20/03/2023");
        LocalDate dateTo = DateUtils.getLocalDateFromString("30/03/2023");
        Integer idHotel = 1;
        Mockito.when(bookingsRepository.findBookingssForHotelBetweenDates(idHotel, dateFrom, dateTo))
                .thenReturn(bookingListMock);
        List<Booking> bookingList = bookingsService.findBookingsForHotelBetweenDates(idHotel, dateFrom, dateTo);
        Assertions.assertEquals(bookingListMock.size(), bookingList.size());
        Mockito.verify(bookingsRepository, Mockito.times(1))
                .findBookingssForHotelBetweenDates(idHotel, dateFrom, dateTo);
        Mockito.verifyNoInteractions(bookingsRepository);

    }

    @Test
    public void testFindBookingById (){
        Optional<Booking> bookingMock = Optional.of(new Booking(1, 1, DateUtils.getLocalDateFromString("20/03/2023"),
                DateUtils.getLocalDateFromString("25/03/2023"), "juan@Gmail.com"));
        Integer idBooking = 1;
        Mockito.when(bookingsRepository.findById(idBooking)).thenReturn(bookingMock);
        Optional<Booking> booking = bookingsService.findBookingById(idBooking);
        Assertions.assertEquals(bookingMock.isPresent(), booking.isPresent());
        Assertions.assertEquals(bookingMock.get(), booking.get());
        Assertions.assertNotNull(booking.get());
        Mockito.verify(bookingsRepository, Mockito.times(1)).findById(idBooking);
        Mockito.verifyNoInteractions(bookingsRepository);

    }

    @Test
    public void testFindBookingByIdNull (){
        Optional<Booking> bookingMock = Optional.empty();
        Integer idBooking = 10;
        Mockito.when(bookingsRepository.findById(idBooking)).thenReturn(bookingMock);
        Optional<Booking> booking = bookingsService.findBookingById(idBooking);
        Assertions.assertEquals(bookingMock.isPresent(), booking.isPresent());
        Mockito.verify(bookingsRepository, Mockito.times(1)).findById(idBooking);
        Mockito.verifyNoInteractions(bookingsRepository);
    }
}

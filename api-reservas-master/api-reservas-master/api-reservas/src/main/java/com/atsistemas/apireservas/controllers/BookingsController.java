package com.atsistemas.apireservas.controllers;

import com.atsistemas.apireservas.controllers.Err.ResourceNotFoundException;
import com.atsistemas.apireservas.dtos.BookingDto;
import com.atsistemas.apireservas.entities.Booking;
import com.atsistemas.apireservas.services.BookingsService;
import com.atsistemas.apireservas.utilities.mappers.BookingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("bookings")
@Validated
public class BookingsController {
    Logger logger = LoggerFactory.getLogger(BookingsController.class);
    private BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveBooking(@RequestBody @Valid BookingDto bookingDto) {
        logger.info("Saving booking");
        bookingsService.saveBooking(BookingMapper.convertToEntity(bookingDto));
        logger.info("Saved booking");
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/date")
    public ResponseEntity searchBookings(@Valid @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom
                                        ) {
        logger.info("Searching bookings");
        List<Booking> bookingList = bookingsService.findBookingfordatefrom(dateFrom);


        return ResponseEntity.ok(BookingMapper.convertEntityListToDtoList(bookingList));
    }





    @GetMapping
    public ResponseEntity searchBookings(@RequestParam Integer idHotel,
                                         @Valid @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom,
                                         @Valid @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo) {
        logger.info("Searching bookings");
        List<Booking> bookingList = bookingsService.findBookingsForHotelBetweenDates(idHotel, dateFrom, dateTo);
        String logMsg = bookingList.isEmpty() ? "No bookings found"
                : String.format("Found %d bookings", bookingList.size());
        logger.info(logMsg);
        return ResponseEntity.ok(BookingMapper.convertEntityListToDtoList(bookingList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findBookingById(@PathVariable(value = "id") Integer id) {
        logger.info(String.format("Find booking by id '%d'", id));
        return bookingsService.findBookingById(id).map(booking -> ResponseEntity.ok(BookingMapper.convertToDto(booking)))
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBookingById(@PathVariable(value = "id") Integer id){
        logger.info(String.format("Cancel booking with id '%d'", id));
        bookingsService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }


}

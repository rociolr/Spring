package com.atsistemas.apireservas.utilities.mappers;

import com.atsistemas.apireservas.dtos.AvailabilityDto;
import com.atsistemas.apireservas.dtos.BookingDto;
import com.atsistemas.apireservas.dtos.HotelDto;
import com.atsistemas.apireservas.entities.Availability;
import com.atsistemas.apireservas.entities.Booking;
import com.atsistemas.apireservas.entities.Hotel;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BookingMapper {

    static ModelMapper modelMapper = new ModelMapper();

    public static Booking convertToEntity (BookingDto bookingDto){
        return modelMapper.map(bookingDto, Booking.class);
    }

    public static BookingDto convertToDto (Booking booking){
        return modelMapper.map(booking, BookingDto.class);
    }

    public static List<BookingDto> convertEntityListToDtoList(List<Booking> bookingsList) {
        List<BookingDto> bookingDtosList = new ArrayList<>();
        bookingsList.forEach(booking -> {
            bookingDtosList.add(convertToDto(booking));
        });
        return bookingDtosList;
    }
}

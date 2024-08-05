package com.atsistemas.apireservas.utilities.mappers;

import com.atsistemas.apireservas.dtos.AvailabilityDto;
import com.atsistemas.apireservas.dtos.HotelDto;
import com.atsistemas.apireservas.entities.Availability;
import com.atsistemas.apireservas.entities.Hotel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HotelMapper {

    static ModelMapper modelMapper = new ModelMapper();


    public static Hotel convertToEntity (HotelDto hotelDto){
        return modelMapper.map(hotelDto, Hotel.class);
    }

    public static HotelDto convertToDto (Hotel hotel){
        return modelMapper.map(hotel, HotelDto.class);
    }

    public static List<HotelDto> convertEntityListToDtoList(List<Hotel> hotelsList) {
        List<HotelDto> hotelDtosList = new ArrayList<>();
        hotelsList.forEach(hotel -> {
            hotelDtosList.add(convertToDto(hotel));
        });
        return hotelDtosList;
    }

}

package com.atsistemas.apireservas.utilities.mappers;

import com.atsistemas.apireservas.dtos.AvailabilityDto;
import com.atsistemas.apireservas.entities.Availability;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class AvailabilitiesMapper {
    static ModelMapper modelMapper = new ModelMapper();

    public static Availability convertToEntity(AvailabilityDto availabilityDto) {
        return modelMapper.map(availabilityDto, Availability.class);
    }

    public static AvailabilityDto convertToDto(Availability availability) {
        return modelMapper.map(availability, AvailabilityDto.class);
    }

    public static List<AvailabilityDto> convertEntityListToDtoList(List<Availability> availabilitiesList) {
        List<AvailabilityDto> availabilitiesDtoList = new ArrayList<>();
        availabilitiesList.forEach(availability -> {
           availabilitiesDtoList.add(convertToDto(availability));
        });
        return availabilitiesDtoList;
    }
}

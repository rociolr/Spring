package com.example.car.mapper;


import com.example.car.dtos.VehicleDto;
import com.example.car.entity.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper
{
    static ModelMapper modelMapper = new ModelMapper();

    public static Vehicle convertToEntity(VehicleDto vehicleDto)
    {
        return modelMapper.map(vehicleDto, Vehicle.class);
    }

    public static VehicleDto convertToDto(Vehicle vehicle)
    {
        return modelMapper.map(vehicle, VehicleDto.class);
    }
}

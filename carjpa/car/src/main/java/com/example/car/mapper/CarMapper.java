package com.example.car.mapper;

import com.example.car.dtos.CarDto;
import com.example.car.entity.Car;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapper
{
    static ModelMapper modelMapper = new ModelMapper();
    public static Car convertToEntity (CarDto carDto){
        return modelMapper.map(carDto, Car.class);
    }

    public static CarDto convertToDto (Car car){
        return modelMapper.map(car, CarDto.class);
    }
}

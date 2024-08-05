package com.example.car.mapper;

import com.example.car.dtos.BrandDto;
import com.example.car.dtos.CarDto;
import com.example.car.entity.Brand;
import com.example.car.entity.Car;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    static ModelMapper modelMapper = new ModelMapper();
    public static Brand convertToEntity (BrandDto brandDto){
        return modelMapper.map(brandDto, Brand.class);
    }

    public static BrandDto convertToDto (Brand brand){
        return modelMapper.map(brand, BrandDto.class);
    }
}

package com.example.car.filter.dtos;

import lombok.Data;

@Data
public class VehicleDto
{
    private Integer id;
    private Integer brand_id;
    private BrandDto branddto;
    private String model;
    private Integer year;


    public VehicleDto()
    {
    }

    public VehicleDto(Integer id, Integer brand_id, String model, Integer year)
    {   this.id = id;
        this.brand_id = brand_id;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString()
    {
        return "" +
                "id='" + id + '\'' +
                "brand_id='" + brand_id + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ' ';
    }
}


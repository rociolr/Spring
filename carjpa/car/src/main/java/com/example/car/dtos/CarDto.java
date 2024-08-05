package com.example.car.dtos;


import lombok.Data;

@Data
public class CarDto extends VehicleDto
{
    private Integer numdoors;
    private  Integer milleage;
    private Double price;
    private String descripcion;
    private String color;
    private String fueltype;



    public CarDto()
    {
    }

    public CarDto(Integer id,Integer brand_id, String model, Integer year, Integer numdoors, Integer milleage , Double price, String descripcion, String color, String fueltype)
    {
        super(id,brand_id, model, year);
        this.numdoors = numdoors;
        this.milleage = milleage;
        this.price=price;
        this.descripcion=descripcion;
        this.color=color;
        this.fueltype=fueltype;
    }

    @Override
    public String toString()
    {
        return "CarDto{" + super.toString() +
                "numDoors=" + numdoors +
                ", milleage=" + milleage +
                ", price=" + price +
                ", descripcion='" + descripcion + '\'' +
                ", color='" + color + '\'' +
                ", fuelType='" + fueltype + '\'' +
                "} " ;
    }
}



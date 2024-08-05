package com.example.car.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "car")
public class Car extends Vehicle
{
    private Integer numdoors;

    private  Integer milleage;
    private Double price;
    private String descripcion;
    private String colour;
    private String fueltype;



    public Car()
    {
    }

    public Car(Integer id,Integer brand_id, String model, Integer year, Integer numdoors, Integer milleage,  Double price, String descripcion, String color, String fueltype)
    {
        super(id,brand_id, model, year);
        this.numdoors = numdoors;
        this.milleage = milleage;
        this.price=price;
        this.descripcion=descripcion;
        this.colour=colour;
        this.fueltype=fueltype;
    }

    @Override
    public String toString()
    {
        return "Car{" + super.toString() +
                "numDoors=" + numdoors +
                ", milleage=" + milleage +
                ", price=" + price +
                ", descripcion='" + descripcion + '\'' +
                ", colour='" + colour + '\'' +
                ", fuelType='" + fueltype + '\'' +
                "} " ;
    }
}

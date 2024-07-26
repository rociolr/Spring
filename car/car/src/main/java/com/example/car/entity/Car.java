package com.example.car.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "car")

public class Car extends Vehicle
{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(numdoors, car.numdoors) && Objects.equals(milleage, car.milleage) && Objects.equals(price, car.price) && Objects.equals(descripcion, car.descripcion) && Objects.equals(colour, car.colour) && Objects.equals(fueltype, car.fueltype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numdoors, milleage, price, descripcion, colour, fueltype);
    }

    private Integer numdoors;

    private  Integer milleage;
    private Double price;
    private String descripcion;
    private String colour;
    private String fueltype;



    public Car()
    {
    }

    public Car(Integer id,Integer brand_id, String model, Integer year, Integer numdoors, Integer milleage,  Double price, String descripcion, String colour, String fueltype)
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
        String brand;
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

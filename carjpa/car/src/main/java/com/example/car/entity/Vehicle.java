package com.example.car.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  class Vehicle

    {    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Integer id;
        private Integer brand_id;
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "brand_id" , insertable = false, updatable = false)
        private Brand brand;
      //  private String brand;
        private String model;
        private Integer year;


    public Vehicle()
        {
        }

    public Vehicle(Integer id, Integer brand_id, String model, Integer year)
        {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.year = year;
        }

        @Override
        public String toString ()
        {
            return "" +
                    "id='" + id + '\'' +
                    "brand_id='" + brand_id + '\'' +
                    ", model='" + model + '\'' +
                    ", year=" + year +
                    ' ';
        }



}
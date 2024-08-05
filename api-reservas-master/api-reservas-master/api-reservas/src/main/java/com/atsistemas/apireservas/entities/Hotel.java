package com.atsistemas.apireservas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotBlank
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private Integer category;


    public Hotel(String name, Integer category) {
        this.name = name;
        this.category = category;
    }
}

package com.atsistemas.apireservas.dtos;

import com.atsistemas.apireservas.entities.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityDto {
    private Integer id;
    private Integer idHotel;
    private LocalDate date;
    private Integer rooms;
    private HotelDto hotel;
}

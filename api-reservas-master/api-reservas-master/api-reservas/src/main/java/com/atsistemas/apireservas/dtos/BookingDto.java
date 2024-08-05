package com.atsistemas.apireservas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Integer id;
    @NotNull
    private Integer idHotel;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate dateTo;
    @NotNull
    @NotEmpty
    private String email;
    private HotelDto hotel;
}

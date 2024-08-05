package com.atsistemas.apireservas.utilities.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookingsFilter {
    @NotNull
    private Integer idHotel;
    @NotNull
    @NotEmpty
    private String dateFrom;
    @NotNull
    @NotEmpty
    private String dateTo;
}

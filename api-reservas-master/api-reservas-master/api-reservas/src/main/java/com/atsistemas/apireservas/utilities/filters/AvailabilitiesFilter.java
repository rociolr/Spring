package com.atsistemas.apireservas.utilities.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilitiesFilter {

    @NotNull(message = "dateFrom must be passed through request")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFrom;
    @NotNull(message = "dateTo must be passed through request")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateTo;
    private String hotelName;
    private Integer category;

}

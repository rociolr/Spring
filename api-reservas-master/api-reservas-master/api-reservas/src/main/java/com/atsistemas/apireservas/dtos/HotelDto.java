package com.atsistemas.apireservas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    private Integer id;
    @NotNull
    @NotEmpty (message = "Hotel name can't be empty")
    private String name;
    @Min(value = 1, message = "Category value must be >= 1 and <=5")
    @Max(value = 5, message = "Category value must be >= 1 and <=5")
    private Integer category;

}

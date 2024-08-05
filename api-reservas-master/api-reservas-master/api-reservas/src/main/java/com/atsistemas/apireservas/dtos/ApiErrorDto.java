package com.atsistemas.apireservas.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDto {

    @JsonProperty
    private HttpStatus status;
    @JsonProperty

    private String path;
    @JsonProperty

    private String error;
    @JsonProperty

    private String message;
    @JsonProperty

    private Instant instant;
}

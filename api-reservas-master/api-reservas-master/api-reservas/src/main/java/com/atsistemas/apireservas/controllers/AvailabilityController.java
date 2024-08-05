package com.atsistemas.apireservas.controllers;

import com.atsistemas.apireservas.dtos.AvailabilityDto;
import com.atsistemas.apireservas.entities.Availability;
import com.atsistemas.apireservas.services.AvailabilitiesService;
import com.atsistemas.apireservas.utilities.filters.AvailabilitiesFilter;
import com.atsistemas.apireservas.utilities.mappers.AvailabilitiesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("availabilities")
public class AvailabilityController {
    Logger logger = LoggerFactory.getLogger(AvailabilityController.class);
    private AvailabilitiesService availabilitiesService;

    public AvailabilityController(AvailabilitiesService availabilitiesService) {
        this.availabilitiesService = availabilitiesService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity openAvailabilities(@RequestParam Integer idHotel, @RequestParam Integer nRooms,
                                             @Valid @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom,
                                             @Valid @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo) {
        logger.info(String.format("Opening availability for %d rooms from %s to %s at the hotel with id %d", nRooms, dateFrom, dateTo, idHotel));
        availabilitiesService.openAvailability(idHotel, nRooms, dateFrom, dateTo);
        logger.info("Successfully completed");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity searchAvailabilities(@Valid AvailabilitiesFilter availabilitiesFilter) {
        logger.info("Searching availabilities");
        List<Availability> availabilitiesList = availabilitiesService.consultAvailability(availabilitiesFilter);
        String logMsg = availabilitiesList.isEmpty() ? "No Availabilities found"
                : String.format("Found %s availabilities", availabilitiesList.size());
        logger.info(logMsg);
       // logger.
        List<AvailabilityDto> availabilitiesDtoList = AvailabilitiesMapper.convertEntityListToDtoList(availabilitiesList);
        return ResponseEntity.ok(availabilitiesDtoList);

    }

}

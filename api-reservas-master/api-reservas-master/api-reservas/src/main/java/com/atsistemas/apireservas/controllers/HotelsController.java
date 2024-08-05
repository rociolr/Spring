package com.atsistemas.apireservas.controllers;

import com.atsistemas.apireservas.controllers.Err.ResourceNotFoundException;
import com.atsistemas.apireservas.dtos.HotelDto;
import com.atsistemas.apireservas.entities.Hotel;
import com.atsistemas.apireservas.services.HotelsService;
import com.atsistemas.apireservas.utilities.mappers.HotelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hotels")
public class HotelsController {
    Logger logger = LoggerFactory.getLogger(HotelsController.class);

    private HotelsService hotelsService;

    public HotelsController(HotelsService service) {
        this.hotelsService = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HotelDto>> findAll() {
        List<Hotel> hotelList = hotelsService.findAllHotels();
        String logMessage = hotelList.isEmpty() ? "No hotels found"
                : String.format("Found %d hotels", hotelList.size());
        logger.info(logMessage);
        return ResponseEntity.ok(HotelMapper.convertEntityListToDtoList(hotelList));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable(name = "id") Integer id) {
        logger.info(String.format("Find hotel by id '%d'", id));
        return hotelsService.findHotelById(id).map(hotel -> ResponseEntity.ok(HotelMapper.convertToDto(hotel)))
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@Valid @RequestBody HotelDto hotelDto) {
        Hotel hotel = HotelMapper.convertToEntity(hotelDto);
        logger.info("Saving hotel");
        hotelsService.createHotel(hotel);
        logger.info("Saved hotel");
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable(value = "id") Integer id, @Valid @RequestBody HotelDto hotelDto) {
        hotelDto.setId(id);
        Hotel hotel = HotelMapper.convertToEntity(hotelDto);
        logger.info(String.format("Updating hotel with id '%d'",id));
        hotelsService.updateHotel(id, hotel);
        logger.info("Hotel successfully updated");
        return new ResponseEntity(HttpStatus.OK);

    }


}

package com.example.car.controller;


import com.example.car.dtos.CarDto;
import com.example.car.entity.Car;
import com.example.car.entity.Product;
import com.example.car.entity.Vehicle;
import com.example.car.mapper.CarMapper;
import com.example.car.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

@Slf4j
public class CarController {
    // Logger log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;
    @PostMapping(value = "/addcar")
    public ResponseEntity<?> addProduct(@RequestBody CarDto carDto){
        Car c = CarMapper.convertToEntity(carDto);
        return ResponseEntity.ok().body(carService.save(c));
    }
    @GetMapping(value="/cars")
    public ResponseEntity<?>getAll(){
//.stream().map(CarMapper:: convertToDto).collect(Collectors.toList())

        return ResponseEntity.ok().body(carService.findAllJoin());
    }


    @DeleteMapping("/deletecar/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        return ResponseEntity.ok().body( carService.delete(id) );
    }

    @PutMapping("/updatecar/{id}")
    public ResponseEntity<CarDto> updateById(@PathVariable Integer id,@RequestBody CarDto carDto){
        Car car=CarMapper.convertToEntity(carDto);
       CarDto carDto1=CarMapper.convertToDto(carService.update(id,car)) ;
        return ResponseEntity.ok().body(carDto1);
    }

    @GetMapping("/findCheapcar/{price}")
    public ResponseEntity<?> findCheapProduct(@PathVariable double price){

        return  ResponseEntity.ok().body(carService.findCheapCar(price));
    }





}

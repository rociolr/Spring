package com.example.car.controller;


import com.example.car.filter.dtos.CarDto;
import com.example.car.entity.Car;
import com.example.car.mapper.CarMapper;
import com.example.car.service.CarService;
import com.example.car.service.CarServiceImpl;
import com.example.car.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@Slf4j
public class CarController {
    // Logger log = LoggerFactory.getLogger(CarController.class);


    @Autowired(required = true)
    @Qualifier("CarServiceImpl")

    private CarService carService;
    @PreAuthorize("hasRole('VENDOR')")

    @PostMapping(value = "/addcar")
    public ResponseEntity<List<Car>> addCar(@RequestBody List<Car> disklist) {
        List<Car> cars = carService.saveAll(disklist).join();
        //   List<Car> lista1 = disklistdto.stream().map(CarMapper:: convertToEntity).collect(Collectors.toList());
        // List<CarDto> listadto =carService.saveAll(lista1)..stream().map(CarMapper:: convertToDto).collect(Collectors.toList());
        return new ResponseEntity(cars, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> getAllJoin() {
//.stream().map(CarMapper:: convertToDto).collect(Collectors.toList())

        // return ResponseEntity.ok().body(carService.findAllJoin());
        //  return carService.save(disklist).thenApply(ResponseEntity::ok);
        // return carService.findAllJoin().thenApply(ResponseEntity::ok);
        List<Car> cars = carService.findAllJoin().join(); // el join espera a que se finalice el hilo y luego devuelve el resultado del completable future
        return new ResponseEntity(cars, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('VENDOR')")
    @DeleteMapping("/deleteallcar")
    public ResponseEntity<List<Car>> deleteallcars(@RequestBody List<Car> disklis) {
        List<Car> cars = carService.deleteAll(disklis).join();
        return new ResponseEntity(cars, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('VENDOR')")
    @PutMapping("/updatecar/{id}")
    public ResponseEntity<CarDto> updateById(@PathVariable Integer id, @RequestBody CarDto carDto) {
        Car car = CarMapper.convertToEntity(carDto);
        CarDto carDto1 = CarMapper.convertToDto(carService.update(id, car));
        return ResponseEntity.ok().body(carDto1);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/findCheapcar/{price}")
    public ResponseEntity<List<Car>> findCheapProduct(@PathVariable double price) {
        List<Car> cars = carService.findCheapCar(price).join();
        return new ResponseEntity(cars, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('VENDOR')")
    @PostMapping(value="/uploadCsv", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            log.info("file vacio");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        } if(file.getOriginalFilename().contains(".csv")){
            log.info("filename is {}", file.getOriginalFilename());
            //  userService.save(file);
            carService.uploadUser(file);
            return ResponseEntity.ok("sucefully upload");
        }
        log.info("file no es un csv");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the file is not a csv");





    }
    @PreAuthorize("hasAnyRole('VENDOR','CLIENT')")
    @GetMapping(value="/downloadUser")
    public ResponseEntity<?> downloadUser() throws IOException
    { //devuelve un archivo csv con todos los usuarios q tenemos ren base de datos

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment","users.csv");
//creamps un array con las respuestas del csv y lo pasamos a bytes
        byte[] csvbytes=carService.UsersCsv().getBytes();
//creamos el cuerpo de la respuessta cpn los headers y csvbytes

        return new ResponseEntity<>(csvbytes,headers,HttpStatus.OK);


//cabecera q con;tendra el mensaje de la respuesta d enuestra llamada http



    }


}

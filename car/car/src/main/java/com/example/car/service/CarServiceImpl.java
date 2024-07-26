package com.example.car.service;


import com.example.car.entity.Brand;
import com.example.car.entity.Car;
import com.example.car.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service("CarServiceImpl")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    private final String[] HEADERS1 = {"id", "brand_id"};
    private final String[] HEADERS = {"id", "brand_id", "model", "colour"};

/*
    public List<Car> findAll(){
        return  carRepository.findAll();
    }
    public Car save(Car c){
        return  carRepository.save(c);

    }
    */

    @Async
    public CompletableFuture<List<Car>> findAllJoin() {
        //guardar el momento en el q se hace la llamada
        long startTime = System.currentTimeMillis();
        List<Car> lista = carRepository.findAllJoin();
        long endTime = System.currentTimeMillis();
        log.info("total time " + (endTime - startTime));

        //completableFuture es un tipo de variable que almacena el resultado de una llamada asincrona
        return CompletableFuture.completedFuture(lista);
    }

    @Async //poner asincrono el metodo
    public CompletableFuture<List<Car>> saveAll(List<Car> lista2) {
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        log.info("total time " + (endTime - startTime));

        List<Car> lista = carRepository.saveAll(lista2);

        //  List<Disk> lista1=discoRepository.findAll();
        //completableFuture es un tipo de variable que almacena el resultado de una llamada asincrona
        return CompletableFuture.completedFuture(lista2);
    }


    public Car update(Integer id, Car c) {
        Optional<Car> existingcar = carRepository.findById(id);
        if (existingcar.isPresent()) {
            c.setId(id);
            return carRepository.save(c);

        } else {
            return null;
        }
    }

    @Async
    public CompletableFuture<List<Car>> deleteAll(List<Car> lista2) {
        carRepository.deleteAll(lista2);
        // int i = 0;
        log.info(lista2.size() + "cars" + "suscefully deleted");
        return CompletableFuture.completedFuture(lista2);
    }

    /*    public List<Car> findCheapProduct(double price){
            return  carRepository.findProductCheapestThan(price);
        }

        */
    @Async
    public CompletableFuture<List<Car>> findCheapCar(double price) {
        return CompletableFuture.completedFuture(carRepository.findCarCheapestThan(price));
    }


    @Override
    public List<Car> uploadUser(MultipartFile file) {
        List<Car> lista = new ArrayList<>();
        try
                (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                 CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            parser.getHeaderMap().forEach((key, value) -> log.info("Header: {} - Index: {}", key, value));

            Iterable<CSVRecord> csvRecords = parser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                //  llenaremos los campos del usuario con los campos de las cabeceras q tenemos aqui arriba
                Car car = new Car();
                //  Brand brand=new Brand();


                //   car.setBrand(new Brand (Integer.parseInt(csvRecord.get("id2"))));
                car.setId(Integer.valueOf(csvRecord.get("id")));
                car.setBrand_id(Integer.valueOf(csvRecord.get("brand_id")));
                car.setModel(csvRecord.get("model"));
                car.setColour(csvRecord.get("colour"));


                lista.add(car);
            }
            List<Car> lista1 = carRepository.saveAll(lista);

            log.info("sucesfulluy loaded user bucle");
        } catch (Exception e) {
            log.info("fail to load users" + e.getMessage(), e);
            throw new RuntimeException("FAIL LOAD USERS" + e);

        }
        return lista;
    }

    @Override
    public String UsersCsv() {
        List<Car> lista = carRepository.findAll();
        //stringbuilder contendra el contenido del csv
        //y la cabecera q ira en primer lugar
        StringBuilder csvContent = new StringBuilder();
        Arrays.toString(HEADERS1);
        csvContent.append(HEADERS1[0]).append(",")
                .append(HEADERS1[1]).append("\n");
        //  csvContent.append(Arrays.toString(HEADERS1));
        //csvContent.append(Arrays.toString(HEADERS1));
        for (Car car : lista) {

            csvContent.append(car.getDescripcion()).append(",")
                    .append(car.getColour()).append("\n");
        }
        return csvContent.toString();
    }


    public Optional<Car> buscarPorId(Integer id) {
       return carRepository.findById(id);
    }
  /*  public String deleteAll(Integer id){
        carRepository.deleteAll(id);
        return "car with id " + id +"suscefully deleted";
    }
  //  public List<Car> findAllJoin(){
   //     return  carRepository.findAllJoin();
   // }
}
*/
}
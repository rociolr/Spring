package com.example.car.service;

import com.example.car.entity.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface CarService {
    public CompletableFuture<List<Car>> findAllJoin();

    CompletableFuture<List<Car>> saveAll(List<Car> lista2);

    public Car update(Integer id, Car c);

    public CompletableFuture<List<Car>> deleteAll(List<Car> lista2);

    public CompletableFuture<List<Car>> findCheapCar(double price);
    List<Car> uploadUser(MultipartFile file);
    String UsersCsv();
    public Optional<Car> buscarPorId(Integer id);

}

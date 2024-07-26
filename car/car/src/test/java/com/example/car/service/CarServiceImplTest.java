package com.example.car.service;

import com.example.car.entity.Car;
import com.example.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void iniciar() {
        MockitoAnnotations.openMocks(this);
        carService = new CarServiceImpl(carRepository);
    }


    @Test
    void testFindAllJoin() {
        // Mocking repository response
        List<Car> mockCars = new ArrayList<>();
        mockCars.add(new Car(1, 1, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));
        mockCars.add(new Car(2, 2, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));
        when(carRepository.findAllJoin()).thenReturn(mockCars);

        // Calling service method
        CompletableFuture<List<Car>> futureCars = carService.findAllJoin();

        // Asserting
        assertDoesNotThrow(() -> {
            List<Car> cars = futureCars.get();
            assertEquals(2, cars.size());
        });
    }

    @Test
    void testSaveAll() {
        // Mocking repository saveAll method
        List<Car> carsToSave = new ArrayList<>();
        carsToSave.add(new Car(1, 1, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));
        carsToSave.add(new Car(2, 2, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));

        when(carRepository.saveAll(any())).thenReturn(carsToSave);

        // Calling service method
        CompletableFuture<List<Car>> futureSavedCars = carService.saveAll(carsToSave);

        // Asserting
        assertDoesNotThrow(() -> {
            List<Car> savedCars = futureSavedCars.get();
            assertEquals(2, savedCars.size());
        });
    }


    @Test
    void testDeleteAll() {
        // Mocking repository deleteAll method
        List<Car> carsToDelete = new ArrayList<>();
        carsToDelete.add(new Car(1, 1, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));
        carsToDelete.add(new Car(2, 2, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));

        // Calling service method
        CompletableFuture<List<Car>> futureDeletedCars = carService.deleteAll(carsToDelete);

        // Asserting
        assertDoesNotThrow(() -> {
            List<Car> deletedCars = futureDeletedCars.get();
            assertEquals(2, deletedCars.size());
        });
    }

    @Test
    void testFindCheapCar() {
        // Mocking repository findCarCheapestThan method
        double maxPrice = 20000.0;
        List<Car> cheapCars = new ArrayList<>();
        cheapCars.add(new Car(1, 1, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));
        cheapCars.add(new Car(2, 2, "Toyota", 2020, 3, 2, 1.3, "e", "e", "e"));

        when(carRepository.findCarCheapestThan(maxPrice)).thenReturn(cheapCars);

        // Calling service method
        CompletableFuture<List<Car>> futureCheapCars = carService.findCheapCar(maxPrice);

        // Asserting
        assertDoesNotThrow(() -> {
            List<Car> result = futureCheapCars.get();
            assertEquals(2, result.size());
        });
    }
}

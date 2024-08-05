package com.example.car.service;



import com.example.car.entity.Car;
import com.example.car.entity.Product;
import com.example.car.entity.Vehicle;
import com.example.car.repository.CarRepository;
import com.example.car.repository.ProducRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CarService
{
    @Autowired
    private CarRepository carRepository;

    public List<Car> findAll(){
        return  carRepository.findAll();
    }
    public Car save(Car c){
        return  carRepository.save(c);

    }
    public   Car update(Integer id, Car c){
        Optional<Car> existingcar=carRepository.findById(id);
        if(existingcar.isPresent()){
            c.setId(id);
            return carRepository.save(c);

        }else{
            return null;
        }
    }
    public String delete(Integer id){
        carRepository.deleteById(id);
        return "car with id " + id +"suscefully deleted";
    }
/*    public List<Car> findCheapProduct(double price){
        return  carRepository.findProductCheapestThan(price);
    }
    */

    public List<Car> findCheapCar(double price){
        return  carRepository.findCarCheapestThan(price);
    }


    public List<Car> findAllJoin(){
        return  carRepository.findAllJoin();
    }
}

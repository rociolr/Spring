package com.example.car.repository;

import com.example.car.entity.Car;
import com.example.car.entity.Product;
import com.example.car.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    //
    //@Query(value = "SELECT p * FROM Product p WHERE price < ?1", nativeQuery = true)  //nativequery=true es para poner consultas de mysql
    //* @Query(value = "SELECT c  FROM Car c WHERE c.price < ?1")// para poner consultas de hpql sin el native query a true
    //en hpql es igual q selec * product..etc

    // List<Car> findProductCheapestThan(double price);
    @Query("FROM Car l JOIN FETCH l.brand ")
    List<Car> findAllJoin();
    //@Query("FROM Car l JOIN FETCH l.Brand a where a.name like %?1%")
    @Query("FROM Car l JOIN FETCH l.brand a where l.price = ?1")
    List<Car> findCarCheapestThan(double price);

    //List<Libro> findByAutorJoinFetch(String name);
}




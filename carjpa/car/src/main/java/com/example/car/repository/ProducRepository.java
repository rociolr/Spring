package com.example.car.repository;

import com.example.car.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProducRepository  extends JpaRepository<Product,Integer> {
    //
    //@Query(value = "SELECT p * FROM Product p WHERE price < ?1", nativeQuery = true)  //nativequery=true es para poner consultas de mysql
    @Query(value = "SELECT p  FROM Product p WHERE p.price < ?1")// para poner consultas de hpql sin el native query a true
    //en hpql es igual q selec * product..etc

    List<Product> findProductCheapestThan( double price);


}

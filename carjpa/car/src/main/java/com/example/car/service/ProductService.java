package com.example.car.service;

import com.example.car.entity.Product;
import com.example.car.repository.ProducRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private  ProducRepository producRepository;

public List<Product> findAll(){
    return  producRepository.findAll();
}
 public Product save(Product p){
    return  producRepository.save(p);

 }
 public   Product update(Integer id, Product p){
    Optional<Product> existingproduct=producRepository.findById(id);
    if(existingproduct.isPresent()){
        p.setId(id);
        return producRepository.save(p);

    }else{
        return null;
    }
 }
 public String delete(Integer id){
    producRepository.deleteById(id);
    return "product with id " + id +"suscefully deleted";
 }
 public List<Product> findCheapProduct(double price){
    return  producRepository.findProductCheapestThan(price);
 }
}
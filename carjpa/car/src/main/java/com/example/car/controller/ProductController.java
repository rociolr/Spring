package com.example.car.controller;

import com.example.car.entity.Product;
import com.example.car.service.ProductService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
   private ProductService productService;

    @PostMapping(value = "/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product p){
       return ResponseEntity.ok().body(productService.save(p));
    }
    @GetMapping(value="/products")
    public ResponseEntity<List<Product>>getAll(){
return ResponseEntity.ok().body(productService.findAll());
    }
    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
   return ResponseEntity.ok().body( productService.delete(id) );
    }

@PutMapping("/updateProduct/{id}")
        public ResponseEntity<Product> updateById(@PathVariable Integer id,@RequestBody Product product){
            return ResponseEntity.ok().body(productService.update(id,product));
    }
    @GetMapping("/findCheapProduct/{price}")
    public ResponseEntity<List<Product>> findCheapProduct(@PathVariable double price){
        return  ResponseEntity.ok().body(productService.findCheapProduct(price));
    }

}



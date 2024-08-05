package com.atsistemas.apireservas.repositories;

import com.atsistemas.apireservas.entities.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class HotelRepositoryTest {
    @Autowired
    private HotelsRepository hotelsRepository;

    @Test
    public void testFindAll (){
        List<Hotel> hotelList = hotelsRepository.findAll();
        Assertions.assertEquals(3, hotelList.size());
    }
    @Test
    public void testFindById (){
        Optional<Hotel> hotel = hotelsRepository.findById(1);
        Assertions.assertEquals(hotel.isPresent(), true);
        Assertions.assertEquals(hotel.get().getName(), "Hotel Playa ballena");
    }

    @Test
    public void testFindByIdNotFound (){
        Optional<Hotel> hotel = hotelsRepository.findById(10);
        Assertions.assertEquals(hotel.isPresent(), false);
    }

    @Test
    @Transactional
    public void testSave(){
        String name = "hotel prueba";
        Hotel hotel = new Hotel(name, 5);
        Hotel savedHotel = hotelsRepository.save(hotel);
        Assertions.assertNotNull(savedHotel);
        Assertions.assertEquals(name, savedHotel.getName());
    }

    @Test
    @Transactional
    public void testDelete(){
        Integer id = 1;
        hotelsRepository.deleteById(id);
        Assertions.assertEquals(hotelsRepository.findById(id).isPresent(), false);
    }

}

package com.atsistemas.apireservas.services;

import com.atsistemas.apireservas.entities.Hotel;
import com.atsistemas.apireservas.repositories.HotelsRepository;
import com.atsistemas.apireservas.services.impl.HotelsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HotelServiceImplTest {
    @Mock
    private HotelsRepository repository;

    private HotelsServiceImpl service;

    @BeforeEach
    void initialize() {

        this.service = new HotelsServiceImpl(repository);
    }

    @Test
    public void testFindAll (){
        List<Hotel> mockList = List.of(
                new Hotel(1, "Hotel playaballena", 5),
                new Hotel(2, "Hotel barcelo", 4),
                new Hotel(3, "Hotel la concha", 4)
        );
        Mockito.when(repository.findAll()).thenReturn(mockList);
        List<Hotel> hotelList = service.findAllHotels();
        Assertions.assertEquals(mockList.size(), hotelList.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindAllNull (){
        List<Hotel> mockList = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(mockList);
        List<Hotel> hotelList = service.findAllHotels();
        Assertions.assertEquals(mockList.size(), hotelList.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindById(){
        Optional<Hotel> mockHotel = Optional.of(new Hotel(1, "Hotel playaballena", 5));
        Integer id = 1;
        Mockito.when(repository.findById(id)).thenReturn(mockHotel);
        Optional<Hotel> hotel = service.findHotelById(id);
        Assertions.assertEquals(hotel.get(), mockHotel.get());
        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

    @Test
    public void testFindByIdNotFound(){
        Optional<Hotel> mockHotel = Optional.empty();
        Integer id = 10;
        Mockito.when(repository.findById(id)).thenReturn(mockHotel);
        Optional<Hotel> hotel = service.findHotelById(id);
        Assertions.assertFalse(hotel.isPresent());
        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

}

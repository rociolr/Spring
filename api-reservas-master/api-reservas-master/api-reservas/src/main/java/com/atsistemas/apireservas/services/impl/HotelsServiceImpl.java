package com.atsistemas.apireservas.services.impl;

import com.atsistemas.apireservas.entities.Hotel;
import com.atsistemas.apireservas.repositories.HotelsRepository;
import com.atsistemas.apireservas.services.HotelsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelsServiceImpl implements HotelsService {

    private HotelsRepository repository;

    public HotelsServiceImpl(HotelsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createHotel(Hotel hotel) {
        repository.save(hotel);
    }

    @Override
    public void updateHotel(Integer idHotel, Hotel hotel) {
        hotel.setId(idHotel);
        repository.save(hotel);
    }

    @Override
    public Optional<Hotel> findHotelById(Integer idHotel) {
        return repository.findById(idHotel);
    }

    @Override
    public List<Hotel> findAllHotels() {
        return repository.findAll();
    }


}

package com.atsistemas.apireservas.controllers;

import com.atsistemas.apireservas.dtos.HotelDto;
import com.atsistemas.apireservas.entities.Hotel;
import com.atsistemas.apireservas.repositories.HotelsRepository;
import com.atsistemas.apireservas.services.HotelsService;
import com.atsistemas.apireservas.utilities.mappers.HotelMapper;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(HotelsController.class)
@AutoConfigureMockMvc
public class HotelsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelsService hotelsService;

    @Test
    public void testFindAll() throws Exception {
        List<Hotel> hotels = List.of(
                new Hotel(1, "Hotel 1", 5),
                new Hotel(2, "Hotel 2", 5)
        );
        Mockito.when(hotelsService.findAllHotels()).thenReturn(hotels);
        mockMvc.perform(MockMvcRequestBuilders.get("/hotels"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Hotel 1"))
                .andExpect(jsonPath("$[0].category").value(5))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Hotel 2"))
                .andExpect(jsonPath("$[1].category").value(5));
        Mockito.verify(hotelsService, Mockito.times(1)).findAllHotels();
        Mockito.verifyNoMoreInteractions(hotelsService);
    }

    @Test
    public void testFindById() throws Exception {
        Integer id = 1;
        Hotel hotel = new Hotel(id, "Hotel 1", 5);
        Mockito.when(hotelsService.findHotelById(id)).thenReturn(Optional.of(hotel));
        mockMvc.perform(MockMvcRequestBuilders.get("/hotels/"+ id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Hotel 1"))
                .andExpect(jsonPath("$.category").value(5));
        Mockito.verify(hotelsService, Mockito.times(1)).findHotelById(id);
        Mockito.verifyNoMoreInteractions(hotelsService);
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        Integer id = 10;
        Mockito.when(hotelsService.findHotelById(id)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/hotels/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        Mockito.verify(hotelsService, Mockito.times(1)).findHotelById(id);
        Mockito.verifyNoMoreInteractions(hotelsService);
    }

    @Test
    public void testSave() throws Exception {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setName("Hotel 1");
        hotelDto.setCategory(5);
        Gson gson = new Gson();
        String jsonString = gson.toJson(hotelDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(hotelsService, Mockito.times(1)).createHotel(Mockito.any(Hotel.class));
        Mockito.verifyNoMoreInteractions(hotelsService);
    }
}

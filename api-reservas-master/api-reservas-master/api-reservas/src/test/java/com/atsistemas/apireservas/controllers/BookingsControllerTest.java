package com.atsistemas.apireservas.controllers;


import com.atsistemas.apireservas.dtos.BookingDto;
import com.atsistemas.apireservas.entities.Booking;
import com.atsistemas.apireservas.entities.Hotel;
import com.atsistemas.apireservas.services.BookingsService;
import com.atsistemas.apireservas.utilities.DateUtils;
import com.atsistemas.apireservas.utilities.mappers.BookingMapper;
import com.atsistemas.apireservas.utilities.serializers.LocalDateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebMvcTest(BookingsController.class)
@AutoConfigureMockMvc
public class BookingsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingsService bookingsService;

    //No he conseguido que funcione este test, pues me da una excepción al formatear las fechas pero
    //realmente tienen el formato correcto.
    @Test
    public void testSaveBooking() throws Exception {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setIdHotel(1);
        bookingDto.setEmail("example@gmail.com");
        bookingDto.setDateFrom(DateUtils.getLocalDateFromString("20/03/2023"));
        bookingDto.setDateTo(DateUtils.getLocalDateFromString("25/03/2023"));
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonString = gson.toJson(bookingDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(bookingsService, Mockito.times(1)).saveBooking(BookingMapper.convertToEntity(bookingDto));
        Mockito.verifyNoMoreInteractions(bookingsService);
    }

    @Test
    public void testSearchBookings() throws Exception {
        List<Booking> bookingsMock = List.of(
                new Booking(1, 1, DateUtils.getLocalDateFromString("20/03/2023"),
                        DateUtils.getLocalDateFromString("25/03/2023"), "example@gmail.com"),
                new Booking(2, 1, DateUtils.getLocalDateFromString("26/03/2023"),
                        DateUtils.getLocalDateFromString("28/03/2023"), "example2@gmail.com")
        );
        LocalDate dateFrom = DateUtils.getLocalDateFromString("20/03/2023");
        LocalDate dateTo = DateUtils.getLocalDateFromString("29/03/2023");
        Mockito.when(bookingsService.findBookingsForHotelBetweenDates(1, dateFrom, dateTo))
                .thenReturn(bookingsMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings")
                        .param("idHotel", "1")
                        .param("dateFrom", "20/03/2023")
                        .param("dateTo", "29/03/2023"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2));


        Mockito.verify(bookingsService, Mockito.times(1))
                .findBookingsForHotelBetweenDates(1, dateFrom, dateTo);
        Mockito.verifyNoMoreInteractions(bookingsService);
    }

}

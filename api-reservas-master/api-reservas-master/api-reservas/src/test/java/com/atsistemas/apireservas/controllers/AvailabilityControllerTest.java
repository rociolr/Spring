package com.atsistemas.apireservas.controllers;


import com.atsistemas.apireservas.entities.Availability;
import com.atsistemas.apireservas.services.AvailabilitiesService;
import com.atsistemas.apireservas.utilities.DateUtils;
import com.atsistemas.apireservas.utilities.filters.AvailabilitiesFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AvailabilityController.class)
@AutoConfigureMockMvc
public class AvailabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvailabilitiesService availabilitiesService;

    @Test
    public void testOpenAvailabilities() throws Exception {
        mockMvc.perform(post("/availabilities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("idHotel", "1")
                        .param("nRooms", "2")
                        .param("dateFrom", "25/03/2023")
                        .param("dateTo", "27/03/2023"))
                .andExpect(result -> status().isCreated())
                .andDo(print());

        verify(availabilitiesService, times(1)).openAvailability(1, 2,
                DateUtils.getLocalDateFromString("25/03/2023"),
                DateUtils.getLocalDateFromString("27/03/2023"));
    }
    @Test
    public void testSearchAvailabilitiesBadRequest() throws Exception {
        AvailabilitiesFilter availabilitiesFilter = new AvailabilitiesFilter();
        availabilitiesFilter.setDateTo(DateUtils.getLocalDateFromString("25/03/2023"));
        List<Availability> availabilityListMock = List.of(
                new Availability(1, DateUtils.getLocalDateFromString("20/03/2023"), 1, 10),
                new Availability(1, DateUtils.getLocalDateFromString("21/03/2023"), 1, 10),
                new Availability(1, DateUtils.getLocalDateFromString("22/03/2023"), 1, 10),
                new Availability(1, DateUtils.getLocalDateFromString("23/03/2023"), 1, 10),
                new Availability(1, DateUtils.getLocalDateFromString("24/03/2023"), 1, 10),
                new Availability(1, DateUtils.getLocalDateFromString("25/03/2023"), 1, 10)
        );

        Mockito.when(availabilitiesService.consultAvailability(availabilitiesFilter)).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/availabilities")
                        .param("dateTo", "25/03/2023"))
                .andExpect(status().isBadRequest())
                .andDo(print());

        verify(availabilitiesService, times(0)).consultAvailability(eq(availabilitiesFilter));
    }
}

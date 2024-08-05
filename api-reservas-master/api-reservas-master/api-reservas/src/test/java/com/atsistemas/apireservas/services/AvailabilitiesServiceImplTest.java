package com.atsistemas.apireservas.services;

import com.atsistemas.apireservas.entities.Availability;
import com.atsistemas.apireservas.repositories.AvailabilitiesRepository;
import com.atsistemas.apireservas.services.impl.AvailabilitiesServiceImpl;
import com.atsistemas.apireservas.utilities.DateUtils;
import com.atsistemas.apireservas.utilities.filters.AvailabilitiesFilter;
import com.atsistemas.apireservas.utilities.specifications.AvailabilitiesSpecifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AvailabilitiesServiceImplTest {
    private AvailabilitiesServiceImpl availabilitiesService;
    @Mock
    private AvailabilitiesRepository availabilitiesRepository;

    @BeforeEach
    public void initialize() {
        this.availabilitiesService = new AvailabilitiesServiceImpl(availabilitiesRepository);
    }

    //Strict stubbing argument mismatch. No he podido solucionarlo
    @Test
    public void testConsultAvailabilities() {
        LocalDate dateFrom = DateUtils.getLocalDateFromString("20/03/2023");
        LocalDate dateTo = DateUtils.getLocalDateFromString("25/03/2023");
        AvailabilitiesFilter filter = new AvailabilitiesFilter();
        filter.setDateFrom(dateFrom);
        filter.setDateTo(dateTo);
        Specification<Availability> availabilitySpecification = AvailabilitiesSpecifications.getSpecification(filter);
        List<Availability> availabilityList = List.of(
                new Availability(1, dateFrom, 1, 5),
                new Availability(2, dateFrom.plusDays(1), 1, 5),
                new Availability(3, dateFrom.plusDays(1), 1, 5),
                new Availability(4, dateFrom.plusDays(1), 1, 5),
                new Availability(5, dateFrom.plusDays(1), 1, 5)
        );
        Mockito.when(availabilitiesRepository.findAll(ArgumentMatchers.eq(availabilitySpecification))).thenReturn(availabilityList);
        List<Availability> result = availabilitiesService.consultAvailability(filter);
        Assertions.assertEquals(availabilityList, result);
    }
}

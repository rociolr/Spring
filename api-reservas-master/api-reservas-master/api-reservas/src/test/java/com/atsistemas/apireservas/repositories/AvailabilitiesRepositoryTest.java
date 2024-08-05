package com.atsistemas.apireservas.repositories;

import com.atsistemas.apireservas.entities.Availability;
import com.atsistemas.apireservas.utilities.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class AvailabilitiesRepositoryTest {
    @Autowired
    private AvailabilitiesRepository availabilitiesRepository;

    @Test
    public void testFindAll() {
        List<Availability> availabilityList = availabilitiesRepository.findAll();
        Assertions.assertEquals(36, availabilityList.size());
    }

    @Test
    public void testFindById() {
        Optional<Availability> availability = availabilitiesRepository.findById(1);
        Availability expectedAvailability = new Availability(1, DateUtils.getLocalDateFromString("20/03/2023"), 1, 10);
        Assertions.assertTrue(availability.isPresent());
        Assertions.assertEquals(availability.get().getDate(), expectedAvailability.getDate());
        Assertions.assertEquals(availability.get().getIdHotel(), expectedAvailability.getIdHotel());
        Assertions.assertEquals(availability.get().getRooms(), expectedAvailability.getRooms());
    }

    @Test
    public void testFindByIdNotFound() {
        Optional<Availability> availability = availabilitiesRepository.findById(100);
        Assertions.assertFalse(availability.isPresent());
    }

    @Test
    @Transactional
    public void testSave() {
        Availability availability = new Availability(1, DateUtils.getLocalDateFromString("20/05/2023"), 1, 10);
        Availability savedAvailability = availabilitiesRepository.save(availability);
        Assertions.assertNotNull(savedAvailability);
        Assertions.assertEquals(availability.getDate(),savedAvailability.getDate());
        Assertions.assertEquals(availability.getIdHotel(),savedAvailability.getIdHotel());
        Assertions.assertEquals(availability.getRooms(),savedAvailability.getRooms());
    }

    @Test
    @Transactional
    public void testDelete() {
        Integer id = 1;
        availabilitiesRepository.deleteById(id);
        Assertions.assertFalse(availabilitiesRepository.findById(id).isPresent());
    }

    @Test
    public void testFindAvailabilityForDateAndIdHotel(){
        LocalDate date = DateUtils.getLocalDateFromString("20/03/2023");
        Integer idHotel = 1;
        Optional<Availability> availability = availabilitiesRepository.findAvailabilityForDateAndIdHotel(date,idHotel);
        Assertions.assertNotNull(availability);

    }
}

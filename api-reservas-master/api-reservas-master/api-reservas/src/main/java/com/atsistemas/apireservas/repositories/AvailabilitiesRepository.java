package com.atsistemas.apireservas.repositories;

import com.atsistemas.apireservas.entities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailabilitiesRepository extends JpaRepository<Availability, Integer>, JpaSpecificationExecutor<Availability> {
    @Query (value = "SELECT a FROM Availability a WHERE a.date = :date AND a.idHotel = :idHotel")
    public Optional<Availability> findAvailabilityForDateAndIdHotel(@Param("date")LocalDate date, @Param("idHotel") Integer idHotel);
}

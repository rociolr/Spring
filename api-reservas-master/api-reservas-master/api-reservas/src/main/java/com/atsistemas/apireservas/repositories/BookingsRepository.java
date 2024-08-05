package com.atsistemas.apireservas.repositories;

import com.atsistemas.apireservas.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT b FROM Booking b " +
            "Where b.idHotel = :idHotel " +
            "AND b.dateFrom >= :dateFrom " +
            "AND b.dateTo <= :dateTo ")
    public List<Booking> findBookingssForHotelBetweenDates(@Param("idHotel") Integer idHotel,
                                                           @Param("dateFrom") LocalDate dateFrom,
                                                           @Param("dateTo") LocalDate dateTo);
    public List<Booking> findByDateFrom (LocalDate dateFrom);
}

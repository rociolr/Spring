package com.atsistemas.apireservas.repositories;

import com.atsistemas.apireservas.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelsRepository extends JpaRepository<Hotel, Integer> {
}

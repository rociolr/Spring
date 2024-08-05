package com.atsistemas.apireservas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "availabilities")
public class Availability {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "availability_date")
    private LocalDate date;
    @Column(name = "idHotel")
    private Integer idHotel;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHotel" , insertable = false, updatable = false)
    private Hotel hotel;
    @Column(name = "rooms")
    private Integer rooms;

    public Availability(Integer id, LocalDate date, Integer idHotel, Integer rooms) {
        this.id = id;
        this.date = date;
        this.idHotel = idHotel;
        this.rooms = rooms;
    }

    public Availability(LocalDate date, Integer idHotel) {
        this.rooms = 0;
        this.date = date;
        this.idHotel = idHotel;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Hotel getHotel() {
        return hotel;
    }
}

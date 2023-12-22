package org.guzman.hibernateapp.hibernatejpaapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ClienteDto {


    private Integer id;

    private String nombre;

    private String apellido;
    private String forma_pago;

    public ClienteDto() {
    }

    public ClienteDto(Integer id, String nombre, String apellido, String forma_pago) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.forma_pago = forma_pago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }
}


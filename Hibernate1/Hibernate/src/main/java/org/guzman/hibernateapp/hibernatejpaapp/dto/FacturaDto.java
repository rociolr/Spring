package org.guzman.hibernateapp.hibernatejpaapp.dto;

import jakarta.persistence.*;
import org.guzman.hibernateapp.hibernatejpaapp.model.Cliente;

public class FacturaDto {

    private Long id;

    private  String descripcion;

    private  Long total;


    private ClienteDto cliente;
}

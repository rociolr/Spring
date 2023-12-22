package org.guzman.hibernateapp.hibernatejpaapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private  String descripcion;
    @Column
    private  Long total;
    @ManyToOne
    @JoinColumn(name = "clienteid")
    private Cliente cliente;

    public Factura() {
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Long getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

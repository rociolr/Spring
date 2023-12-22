package org.guzman.hibernateapp.hibernatejpaapp.repository;

import org.guzman.hibernateapp.hibernatejpaapp.model.Factura;

import java.util.List;

public interface FacturaRepository {
    List<Factura> getAllFacturas();
    Factura createFactura(Factura factura);
}

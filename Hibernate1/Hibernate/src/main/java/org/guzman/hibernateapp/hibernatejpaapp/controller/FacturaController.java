package org.guzman.hibernateapp.hibernatejpaapp.controller;

import org.guzman.hibernateapp.hibernatejpaapp.model.Factura;
import org.guzman.hibernateapp.hibernatejpaapp.repository.FacturaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("facturas")
public class FacturaController {
    @Autowired
    private FacturaRepositoryImpl facturaRepository;

    @GetMapping
    public List<Factura> getAllFacturas() {
        return facturaRepository.getAllFacturas();
    }

    @PostMapping
    public Factura createFactura(@RequestBody Factura factura) {
        return facturaRepository.createFactura(factura);
    }
}

package org.guzman.hibernateapp.hibernatejpaapp.service;

import org.guzman.hibernateapp.hibernatejpaapp.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();
    void cambiarNombreCliente(Integer id, Cliente cliente);
    Cliente buscarPorId(Integer id);
    void crearCliente(Cliente cliente);
    void eliminarCliente(Integer clienteId);
     List<Cliente> obtenerClientesPorNombre(String nombre);

}

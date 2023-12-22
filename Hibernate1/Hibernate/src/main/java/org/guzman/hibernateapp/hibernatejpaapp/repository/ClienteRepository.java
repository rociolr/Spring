package org.guzman.hibernateapp.hibernatejpaapp.repository;

import org.guzman.hibernateapp.hibernatejpaapp.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    Cliente getClienteById(Integer id);
    List<Cliente> getAllClientes();
    void updateNombreCliente(Integer clienteId, Cliente cliente);
  void deleteCliente(Integer clienteId);
    void createCliente(Cliente cliente);
    List<Cliente> getClientesByNombre(String nombre);
}

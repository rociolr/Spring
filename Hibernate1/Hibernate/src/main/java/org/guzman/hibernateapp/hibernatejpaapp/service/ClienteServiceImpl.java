package org.guzman.hibernateapp.hibernatejpaapp.service;

import org.guzman.hibernateapp.hibernatejpaapp.model.Cliente;
import org.guzman.hibernateapp.hibernatejpaapp.repository.ClienteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepositoryImpl clienteRepository;
@Transactional
    public List<Cliente> listarClientes() {
        return clienteRepository.getAllClientes();
    }




    @Transactional

    public void cambiarNombreCliente(Integer id, Cliente cliente) {
         clienteRepository.updateNombreCliente(id, cliente);
    }
@Transactional
    @Override
    public Cliente buscarPorId(Integer id) {
      return  clienteRepository.getClienteById(id);
    }

    @Override
    public void crearCliente(Cliente cliente) {
         clienteRepository.createCliente(cliente);
    }

    @Override
    public void eliminarCliente(Integer clienteId) {
     clienteRepository.deleteCliente(clienteId);
    }

    @Override
    public List<Cliente> obtenerClientesPorNombre(String nombre) {
        return null;
    }
}

package org.guzman.hibernateapp.hibernatejpaapp.controller;

import org.guzman.hibernateapp.hibernatejpaapp.dto.ClienteDto;
import org.guzman.hibernateapp.hibernatejpaapp.mapper.ClienteMapper;
import org.guzman.hibernateapp.hibernatejpaapp.model.Cliente;
import org.guzman.hibernateapp.hibernatejpaapp.repository.ClienteRepositoryImpl;
import org.guzman.hibernateapp.hibernatejpaapp.service.ClienteService;
import org.guzman.hibernateapp.hibernatejpaapp.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return clientes.stream().map(ClienteMapper::convertToDto).collect(Collectors.toList());

    }
    @GetMapping(value = "/{genero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> obtenerClientesPorNombre(@PathVariable(value = "nombre") String nombre) {
        return clienteService.obtenerClientesPorNombre(nombre);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public ClienteDto obtenerPelicula(@PathVariable Integer id) {

        return ClienteMapper.convertToDto(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity createCliente(@RequestBody ClienteDto clienteDto) {
        Cliente cliente=ClienteMapper.convertToEntity(clienteDto);

        clienteService.crearCliente(cliente);
        return new ResponseEntity(HttpStatus.CREATED);
        //nose le añade id no al put
    }
    @PutMapping("/{id}")
    public ResponseEntity updateCliente(@PathVariable Integer id, @RequestBody ClienteDto clienteDto) {
        clienteDto.setId(id);
        Cliente cliente = ClienteMapper.convertToEntity(clienteDto);
       // logger.info(String.format("Updating hotel with id '%d'",id));
        clienteService.cambiarNombreCliente(id, cliente);

       // hotelsService.updateHotel(id, hotel);
       // logger.info("Hotel successfully updated");
        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id){
          clienteService.eliminarCliente(id);
    }
}

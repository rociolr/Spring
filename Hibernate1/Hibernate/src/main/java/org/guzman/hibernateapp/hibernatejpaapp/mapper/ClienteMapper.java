package org.guzman.hibernateapp.hibernatejpaapp.mapper;

import org.guzman.hibernateapp.hibernatejpaapp.dto.ClienteDto;
import org.guzman.hibernateapp.hibernatejpaapp.dto.FacturaDto;
import org.guzman.hibernateapp.hibernatejpaapp.model.Cliente;
import org.guzman.hibernateapp.hibernatejpaapp.model.Factura;
import org.modelmapper.ModelMapper;

public class ClienteMapper {
    static ModelMapper modelMapper = new ModelMapper();

    public static Cliente convertToEntity (ClienteDto clienteDto){
        return modelMapper.map(clienteDto, Cliente.class);
    }

    public static ClienteDto convertToDto (Cliente cliente){
        return modelMapper.map(cliente , ClienteDto.class);
    }

}

package org.guzman.hibernateapp.hibernatejpaapp.mapper;
import org.modelmapper.ModelMapper;
import org.guzman.hibernateapp.hibernatejpaapp.dto.FacturaDto;
import org.guzman.hibernateapp.hibernatejpaapp.model.Factura;

public class FacturaMapper {
    static ModelMapper modelMapper = new ModelMapper();

    public static Factura convertToEntity (FacturaDto bookingDto){
        return modelMapper.map(bookingDto, Factura.class);
    }

    public static FacturaDto convertToDto (Factura factura){
        return modelMapper.map(factura , FacturaDto.class);
    }

}

package co.com.reto.rabbitmq.mapper;

import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.rabbitmq.dto.InvocacionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvocacionMapper {

    Invocacion dtoToDomain(InvocacionDTO invocacionDTO);
    InvocacionDTO domainToDTO(Invocacion invocacion);
}

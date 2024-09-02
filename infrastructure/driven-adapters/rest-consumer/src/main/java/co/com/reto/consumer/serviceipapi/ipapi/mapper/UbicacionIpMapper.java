package co.com.reto.consumer.serviceipapi.ipapi.mapper;

import co.com.reto.consumer.serviceipapi.ipapi.dto.IdiomaResponse;
import co.com.reto.consumer.serviceipapi.ipapi.dto.UbicacionIpResponse;
import co.com.reto.model.ubicacionip.UbicacionIp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UbicacionIpMapper {

    UbicacionIpMapper MAPPER = Mappers.getMapper(UbicacionIpMapper.class);

    @Mapping(target = "pais", source = "ubicacionIpResponse.country_name")
    @Mapping(target = "codigoISO", source = "ubicacionIpResponse.country_code")
    @Mapping(target = "latitud", source = "ubicacionIpResponse.latitude")
    @Mapping(target = "longitud", source = "ubicacionIpResponse.longitude")
    @Mapping(target = "idiomas", source = "location.languages", qualifiedByName = "mapLanguages")
    UbicacionIp restResponseToUbicacionIp(UbicacionIpResponse ubicacionIpResponse);

    @Named("mapLanguages")
    default List<String> mapLanguages(List<IdiomaResponse> languages) {
        return languages.stream()
                .map(IdiomaResponse::getName)
                .collect(Collectors.toList());
    }
}

package co.com.reto.consumer.servicerates.rate.mapper;

import co.com.reto.consumer.servicerates.rate.dto.RateResponse;
import co.com.reto.model.tarifa.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RateMapper {

    RateMapper MAPPER = Mappers.getMapper(RateMapper.class);

    Rate restResponseToRate(RateResponse rateResponse);
}

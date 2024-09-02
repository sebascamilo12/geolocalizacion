package co.com.reto.consumer.serviceapitimezone.timezone.mapper;

import co.com.reto.consumer.serviceapitimezone.timezone.dto.TimeZoneResponse;
import co.com.reto.model.timezone.Timezone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TimeZoneMapper {

    TimeZoneMapper MAPPER = Mappers.getMapper(TimeZoneMapper.class);

    Timezone restResponseToTimezone(TimeZoneResponse timeZoneResponse);
}

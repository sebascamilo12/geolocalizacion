package co.com.reto.consumer.serviceapitimezone;


import co.com.reto.consumer.serviceapitimezone.timezone.dto.TimeZoneResponse;
import co.com.reto.consumer.serviceapitimezone.timezone.mapper.TimeZoneMapper;
import co.com.reto.model.timezone.Timezone;
import co.com.reto.model.timezone.gateways.TimezoneGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class RestConsumerTimeZone implements TimezoneGateway {

    private final WebClient webClient;


    public RestConsumerTimeZone(@Qualifier("timeZoneApiWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @Cacheable("horarios")
    public Mono<Timezone> obtenerTimeZones(String codigoIso) {
        return webClient
                .get()
                .uri("/v3.1/alpha/{codigoIso}?fields=timezones", codigoIso)
                .retrieve()
                .bodyToMono(TimeZoneResponse.class)
                .map(TimeZoneMapper.MAPPER::restResponseToTimezone);
    }
}

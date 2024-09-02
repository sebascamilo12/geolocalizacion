package co.com.reto.model.timezone.gateways;

import co.com.reto.model.timezone.Timezone;
import reactor.core.publisher.Mono;

public interface TimezoneGateway {

    Mono<Timezone> obtenerTimeZones(String codigoIso);
}

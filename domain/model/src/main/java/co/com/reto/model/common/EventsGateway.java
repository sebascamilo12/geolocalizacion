package co.com.reto.model.common;

import co.com.reto.model.invocacion.Invocacion;
import reactor.core.publisher.Mono;

public interface EventsGateway {
    Mono<Void> emit(Invocacion invocacion);
}

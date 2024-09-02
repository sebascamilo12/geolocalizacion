package co.com.reto.model.invocacion.gateways;

import co.com.reto.model.invocacion.Invocacion;
import reactor.core.publisher.Mono;

public interface InvocacionRepository {

    Mono<Invocacion> guardarInvocacion(Invocacion invocacion);

}

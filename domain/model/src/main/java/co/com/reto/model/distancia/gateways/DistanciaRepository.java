package co.com.reto.model.distancia.gateways;

import co.com.reto.model.distancia.Distancia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DistanciaRepository {

    Mono<Distancia> guardarDistancia(Distancia distancia);
    Mono<Distancia> obtenerDistancia(String codigoIso);
    Flux<Distancia> obtenerDistancias();
    Mono<Double> obtenerPromedioDistancia();
}

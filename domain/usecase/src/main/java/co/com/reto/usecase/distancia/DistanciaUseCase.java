package co.com.reto.usecase.distancia;

import co.com.reto.model.distancia.Distancia;
import co.com.reto.model.distancia.gateways.DistanciaRepository;
import reactor.core.publisher.Mono;

public class DistanciaUseCase {

    private final DistanciaRepository distanciaRepository;

    public DistanciaUseCase(DistanciaRepository distanciaRepository) {
        this.distanciaRepository = distanciaRepository;
    }

    public Mono<Distancia> guardarDistancia(Distancia distancia){

            return distanciaRepository.obtenerDistancia(distancia.getCodigoIso())
                    .switchIfEmpty(distanciaRepository.guardarDistancia(distancia));
    }

}

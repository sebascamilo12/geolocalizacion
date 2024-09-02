package co.com.reto.usecase.reporte;

import co.com.reto.model.distancia.Distancia;
import co.com.reto.model.distancia.gateways.DistanciaRepository;
import co.com.reto.model.reportedistancia.ReporteDistancia;
import reactor.core.publisher.Mono;
import java.util.Optional;


public class ReporteUseCase {

    private final DistanciaRepository distanciaRepository;

    public ReporteUseCase(DistanciaRepository distanciaRepository) {
        this.distanciaRepository = distanciaRepository;
    }

    public Mono<ReporteDistancia> obtenerReporteDistancia() {
        return distanciaRepository.obtenerDistancias()
                .collectList()
                .flatMap(distancias -> {
                    if (distancias.isEmpty()) {
                        return Mono.just(ReporteDistancia.builder().build());
                    }
                    String distanciaCercana = obtenerDistancia(distancias.stream().findFirst());
                    String distanciaLejana = obtenerDistancia(Optional.of(distancias.get(distancias.size() - 1)));
                    ReporteDistancia reporteDistancia = ReporteDistancia.builder()
                            .distanciaCercana(distanciaCercana)
                            .distanciaLejana(distanciaLejana)
                            .promedio(null)
                            .build();
                    return distanciaRepository.obtenerPromedioDistancia()
                            .map(promedio -> reporteDistancia.toBuilder().promedio(String.valueOf(promedio).concat(" Kms")).build());
                });
    }

    private String obtenerDistancia(Optional<Distancia> distanciaOptional) {
        return distanciaOptional.map(distancia -> distancia.getDistanciaABuenosAires().toString().concat(" Kms"))
                .orElse(null);

    }


}

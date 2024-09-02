package co.com.reto.usecase.reporte;

import co.com.reto.model.distancia.Distancia;
import co.com.reto.model.distancia.gateways.DistanciaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReporteUseCaseTest {

    @InjectMocks
    private ReporteUseCase reporteUseCase;
    @Mock
    private DistanciaRepository distanciaRepository;
    private Distancia distancia = new Distancia("COP", 1240.);
    private Double doubleDistancia= 452.0;

    @Test
    void obtenerReporteDistancia() {

        when(distanciaRepository.obtenerDistancias()).thenReturn(Flux.just(distancia));
        when(distanciaRepository.obtenerPromedioDistancia()).thenReturn(Mono.just(doubleDistancia));

        StepVerifier.create(reporteUseCase.obtenerReporteDistancia())
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();

        verify(distanciaRepository,times(1)).obtenerDistancias();
        verify(distanciaRepository,times(1)).obtenerPromedioDistancia();
    }

    @Test
    void obtenerReporteDistanciaVacio() {

        when(distanciaRepository.obtenerDistancias()).thenReturn(Flux.empty());


        StepVerifier.create(reporteUseCase.obtenerReporteDistancia())
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();

        verify(distanciaRepository,times(1)).obtenerDistancias();
        verify(distanciaRepository,times(0)).obtenerPromedioDistancia();
    }
}
package co.com.reto.usecase.distancia;

import co.com.reto.model.distancia.Distancia;
import co.com.reto.model.distancia.gateways.DistanciaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DistanciaUseCaseTest {
    @InjectMocks
    private DistanciaUseCase distanciaUseCase;
    @Mock
    private DistanciaRepository distanciaRepository;
    private Distancia distancia = new Distancia("COP", 1240.);

    @Test
    void guardarDistancia() {

        when(distanciaRepository.obtenerDistancia(anyString())).thenReturn(Mono.just(distancia));
        when(distanciaRepository.guardarDistancia(distancia)).thenReturn(Mono.just(distancia));

        StepVerifier.create(distanciaUseCase.guardarDistancia(distancia))
                .consumeNextWith(resultado -> {
                    Assertions.assertEquals("COP", resultado.getCodigoIso());
                })
                .expectComplete()
                .verify();

        verify(distanciaRepository, times(1)).obtenerDistancia(anyString());


    }
}
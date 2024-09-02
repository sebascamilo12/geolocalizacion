package co.com.reto.usecase.tarifa;

import co.com.reto.model.tarifa.Rate;
import co.com.reto.model.tarifa.gateways.TarifaGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarifaUseCaseTest {

    @InjectMocks
    private TarifaUseCase tarifaUseCase;
    @Mock
    private TarifaGateway tarifaGateway;
    private Rate rate  = new Rate();


    @Test
    void obtenerTarifas() {

        when(tarifaGateway.obtenerTarifas()).thenReturn(Mono.just(rate));

        StepVerifier.create(tarifaUseCase.obtenerTarifas())
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();

        verify(tarifaGateway, times(1)).obtenerTarifas();
    }
}
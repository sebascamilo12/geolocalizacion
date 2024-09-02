package co.com.reto.usecase.invocacion;

import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.model.invocacion.gateways.InvocacionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvocacionUseCaseTest {

    @InjectMocks
    private InvocacionUseCase invocacionUseCase;
    @Mock
    private InvocacionRepository invocacionRepository;
    private Invocacion invocacion = new Invocacion("ES", LocalDateTime.now());

    @Test
    void guardarInvocacion() {

        when(invocacionRepository.guardarInvocacion(invocacion)).thenReturn(Mono.just(invocacion));

        StepVerifier.create(invocacionUseCase.guardarInvocacion(invocacion))
                .consumeNextWith(resultado ->{
                    Assertions.assertEquals("ES", resultado.getCodigoIso());
                })
                .expectComplete()
                .verify();

        verify(invocacionRepository, times(1)).guardarInvocacion(invocacion);
    }
}
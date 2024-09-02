package co.com.reto.usecase.invocacion;

import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.model.invocacion.gateways.InvocacionRepository;
import reactor.core.publisher.Mono;


public class InvocacionUseCase {

    private final InvocacionRepository invocacionRepository;

    public InvocacionUseCase(InvocacionRepository invocacionRepository) {
        this.invocacionRepository = invocacionRepository;
    }

    public Mono<Invocacion> guardarInvocacion(Invocacion invocacion){
        return invocacionRepository.guardarInvocacion(invocacion);
    }
}

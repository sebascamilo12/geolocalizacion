package co.com.reto.api;

import co.com.reto.usecase.ubicacionip.UbicacionIpUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UbicacionIpHandler {
    
    private final UbicacionIpUseCase ubicacionIpUseCase;

    public Mono<ServerResponse> obtenerUbicacionIp(ServerRequest serverRequest) {

        return ubicacionIpUseCase.obtenerUbicacionIp(serverRequest.pathVariable("ip"))
                .flatMap(ubicacionIp -> ServerResponse.ok().bodyValue(ubicacionIp));
    }    

}

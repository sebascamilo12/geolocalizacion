package co.com.reto.api;

import co.com.reto.model.moneda.gateways.MonedaRepository;
import co.com.reto.usecase.tarifa.TarifaUseCase;
import co.com.reto.usecase.ubicacionip.UbicacionIpUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final UbicacionIpUseCase ubicacionIpUseCase;
    private final MonedaRepository monedaRepository;
    private final TarifaUseCase tarifaUseCase;



    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {

        return ubicacionIpUseCase.obtenerUbicacionIp(serverRequest.pathVariable("ip"))
                .flatMap(ubicacionIp -> ServerResponse.ok().bodyValue(ubicacionIp));

    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {

//        return timeZoneUseCase.obtenerTimeZones(serverRequest.pathVariable("codigoIso"))
//                .flatMap(timezone -> ServerResponse.ok().bodyValue(timezone));

//                return tarifaUseCase.obtenerYGuardarTarifas()
//                .flatMap(unused -> ServerResponse.accepted().build());
                return monedaRepository.obtenerMoneda("COP")
                .flatMap(moneda -> ServerResponse.ok().bodyValue(moneda.getCodigoMoneda()));

    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // usecase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}

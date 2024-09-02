package co.com.reto.model.ubicacionip.gateways;

import co.com.reto.model.ubicacionip.UbicacionIp;
import reactor.core.publisher.Mono;


public interface UbicacionIpGateway {

    Mono<UbicacionIp> obtenerUbicacionIp(String ip);
}

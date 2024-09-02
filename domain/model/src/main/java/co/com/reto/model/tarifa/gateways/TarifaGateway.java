package co.com.reto.model.tarifa.gateways;

import co.com.reto.model.tarifa.Rate;
import reactor.core.publisher.Mono;

public interface TarifaGateway {

    Mono<Rate> obtenerTarifas();
}

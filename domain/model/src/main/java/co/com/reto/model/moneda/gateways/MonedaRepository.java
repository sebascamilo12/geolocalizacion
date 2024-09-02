package co.com.reto.model.moneda.gateways;

import co.com.reto.model.moneda.Moneda;
import reactor.core.publisher.Mono;

public interface MonedaRepository {

    Mono<Moneda> obtenerMoneda(String codigoIso);
}

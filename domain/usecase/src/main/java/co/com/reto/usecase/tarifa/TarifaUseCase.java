package co.com.reto.usecase.tarifa;

import co.com.reto.model.tarifa.Rate;
import co.com.reto.model.tarifa.gateways.TarifaGateway;
import reactor.core.publisher.Mono;

public class TarifaUseCase {

    private final TarifaGateway tarifaGateway;


    public TarifaUseCase(TarifaGateway tarifaGateway) {
        this.tarifaGateway = tarifaGateway;
    }

    public Mono<Rate> obtenerTarifas(){
        return tarifaGateway.obtenerTarifas();
    }
}

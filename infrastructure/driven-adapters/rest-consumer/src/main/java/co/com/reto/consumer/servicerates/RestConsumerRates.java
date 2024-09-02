package co.com.reto.consumer.servicerates;

import co.com.reto.consumer.servicerates.rate.dto.RateResponse;
import co.com.reto.consumer.servicerates.rate.mapper.RateMapper;
import co.com.reto.model.tarifa.Rate;
import co.com.reto.model.tarifa.gateways.TarifaGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RestConsumerRates implements TarifaGateway {

    private final WebClient client;
    private static final Logger logger = LoggerFactory.getLogger(RestConsumerRates.class);

    public RestConsumerRates(@Qualifier("ratesWebClient") WebClient client) {
        this.client = client;
    }

    @Value("${adapter.restconsumer-rates.app-id}")
    private String appId;

    @Override
    @Cacheable("tarifas")
    public Mono<Rate> obtenerTarifas() {
        logger.info("Consumiendo servicio tarifas");
        return client
                .get()
                .uri("/api/latest.json?app_id="+appId)
                .retrieve()
                .bodyToMono(RateResponse.class)
                .map(rateResponse -> {
                   logger.info("Finalizando consumo tarifas");
                    return  RateMapper.MAPPER.restResponseToRate(rateResponse);
                });
    }

}

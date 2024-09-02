package co.com.reto.consumer.servicerates;

import co.com.reto.consumer.serviceipapi.ipapi.dto.UbicacionIpResponse;
import co.com.reto.consumer.servicerates.rate.dto.RateResponse;
import co.com.reto.consumer.servicerates.rate.mapper.RateMapper;
import co.com.reto.model.tarifa.Rate;
import co.com.reto.model.tarifa.gateways.TarifaGateway;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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

    //    @Value("${adapter.restconsumer.access-key}")
//    private final String accessKey;


    // these methods are an example that illustrates the implementation of WebClient.
    // You should use the methods that you implement from the Gateway from the domain.
    @CircuitBreaker(name = "testGet" /*, fallbackMethod = "testGetOk"*/)
    public Mono<UbicacionIpResponse> testGet() {
        return client
                .get()
                .uri("/{ip}?access_key=a293e059a7f8b6ce3461fa1e3f3c0f46", "125.145")
                .retrieve()
                .bodyToMono(UbicacionIpResponse.class);
    }

// Possible fallback method
//    public Mono<String> testGetOk(Exception ignored) {
//        return client
//                .get() // TODO: change for another endpoint or destination
//                .retrieve()
//                .bodyToMono(String.class);
//    }

//    @CircuitBreaker(name = "testPost")
//    public Mono<UbicacionIpResponse> testPost() {
//        ObjectRequest request = ObjectRequest.builder()
//            .val1("exampleval1")
//            .val2("exampleval2")
//            .build();
//        return client
//                .post()
//                .body(Mono.just(request), ObjectRequest.class)
//                .retrieve()
//                .bodyToMono(UbicacionIpResponse.class);
//    }

    @Override
    @Cacheable("tarifas")
    public Mono<Rate> obtenerTarifas() {
        logger.info("Consumiendo servicio tarifas");
        return client
                .get()
                .uri("/api/latest.json?app_id=2f699d0496654411abcf3410b81f9038")
                .retrieve()
                .bodyToMono(RateResponse.class)
                .map(rateResponse -> {
                   logger.info("Finalizando consumo tarifas");
                    return  RateMapper.MAPPER.restResponseToRate(rateResponse);
                });
    }

}

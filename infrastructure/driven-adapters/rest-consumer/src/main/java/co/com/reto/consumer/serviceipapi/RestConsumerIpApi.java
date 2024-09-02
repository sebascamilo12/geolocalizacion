package co.com.reto.consumer.serviceipapi;

import co.com.reto.consumer.serviceipapi.ipapi.dto.UbicacionIpResponse;
import co.com.reto.consumer.serviceipapi.ipapi.mapper.UbicacionIpMapper;
import co.com.reto.model.ubicacionip.UbicacionIp;
import co.com.reto.model.ubicacionip.gateways.UbicacionIpGateway;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class RestConsumerIpApi implements UbicacionIpGateway {

    private final WebClient client;

    private static final Logger logger = LoggerFactory.getLogger(RestConsumerIpApi.class);

    public RestConsumerIpApi(@Qualifier("ipApiWebClient") WebClient client) {
        this.client = client;
    }

    @Value("${adapter.restconsumer-ipapi.access-key}")
    private String accessKey;

    @Override
    @CircuitBreaker(name = "restConsumer" , fallbackMethod = "fallBackObtenerInformacion")
    public Mono<UbicacionIp> obtenerUbicacionIp(String ip) {
        return client
                .get()
                .uri("/{ip}?access_key="+accessKey, ip)
                .retrieve()
                .bodyToMono(UbicacionIpResponse.class)
                .map(UbicacionIpMapper.MAPPER::restResponseToUbicacionIp);
    }

    public Mono<UbicacionIp> fallBackObtenerInformacion(String ip, WebClientResponseException ex) {
        logger.error("Error al obtener la ubicación para la IP: {}. Error: {}", ip, ex.getMessage());
        return Mono.error(new RuntimeException("Ocurrió un error al obtener la ubicación para la IP: " + ip, ex));
    }
}

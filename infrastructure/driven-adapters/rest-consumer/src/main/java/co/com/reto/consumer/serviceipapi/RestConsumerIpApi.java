package co.com.reto.consumer.serviceipapi;

import co.com.reto.consumer.serviceipapi.ipapi.dto.UbicacionIpResponse;
import co.com.reto.consumer.serviceipapi.ipapi.mapper.UbicacionIpMapper;
import co.com.reto.model.ubicacionip.UbicacionIp;
import co.com.reto.model.ubicacionip.gateways.UbicacionIpGateway;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RestConsumerIpApi implements UbicacionIpGateway {

    private final WebClient client;

    public RestConsumerIpApi(@Qualifier("ipApiWebClient") WebClient client) {
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
    public Mono<UbicacionIp> obtenerUbicacionIp(String ip) {
        return client
                .get()
                .uri("/{ip}?access_key=a293e059a7f8b6ce3461fa1e3f3c0f46", ip)
                .retrieve()
                .bodyToMono(UbicacionIpResponse.class)
                .map(UbicacionIpMapper.MAPPER::restResponseToUbicacionIp);
    }
}

package co.com.reto.api;

import co.com.reto.api.reporte.ReporteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(UbicacionIpHandler ubicacionIpHandler, ReporteHandler reporteHandler) {
        return route(GET("/api/v1/ubicacion/{ip}"), ubicacionIpHandler::obtenerUbicacionIp)
                .andRoute(GET("/api/v1/reporte"), request -> reporteHandler.reporteDistancia());
    }
}

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
    public RouterFunction<ServerResponse> routerFunction(Handler handler, ReporteHandler reporteHandler) {
        return route(GET("/api/usecase/path/{ip}"), handler::listenGETUseCase)
                .andRoute(GET("/api/usecase/otherpath"), handler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase))
                .andRoute(GET("/api/reporte"), request -> reporteHandler.reporteDistancia());
    }
}

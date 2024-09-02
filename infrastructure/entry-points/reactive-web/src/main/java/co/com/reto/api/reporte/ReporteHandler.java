package co.com.reto.api.reporte;


import co.com.reto.usecase.reporte.ReporteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class ReporteHandler {

    private final ReporteUseCase reporteUseCase;

    public Mono<ServerResponse> reporteDistancia() {

        return reporteUseCase.obtenerReporteDistancia()
                .flatMap(reporteDistancia -> {
                    if (reporteDistancia.getDistanciaCercana() == null) {
                        return ServerResponse.noContent().build();
                    }
                    return ServerResponse.ok().bodyValue(reporteDistancia);
                });

    }


}

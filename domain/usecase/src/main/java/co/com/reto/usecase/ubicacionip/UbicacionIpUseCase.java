package co.com.reto.usecase.ubicacionip;

import co.com.reto.model.common.EventsGateway;
import co.com.reto.model.distancia.Distancia;
import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.model.moneda.Moneda;
import co.com.reto.model.moneda.gateways.MonedaRepository;
import co.com.reto.model.timezone.Timezone;
import co.com.reto.model.timezone.gateways.TimezoneGateway;
import co.com.reto.model.ubicacionip.UbicacionIp;
import co.com.reto.model.ubicacionip.gateways.UbicacionIpGateway;
import co.com.reto.usecase.distancia.DistanciaUseCase;
import co.com.reto.usecase.tarifa.TarifaUseCase;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


public class UbicacionIpUseCase {

    private final UbicacionIpGateway ubicacionIpGateway;
    private final MonedaRepository monedaRepository;
    private final TimezoneGateway timezoneGateway;
    private final DistanciaUseCase distanciaUseCase;
    private final EventsGateway eventsGateway;
    private final TarifaUseCase tarifaUseCase;


    private static final Double LATITUD_BUENOS_AIRES = -38.416097;
    private static final Double LONGITUD_BUENOS_AIRES = -63.616672;


    public UbicacionIpUseCase(UbicacionIpGateway ubicacionIpGateway, MonedaRepository monedaRepository, TimezoneGateway timezoneGateway, DistanciaUseCase distanciaUseCase, EventsGateway eventsGateway, TarifaUseCase tarifaUseCase) {
        this.ubicacionIpGateway = ubicacionIpGateway;
        this.monedaRepository = monedaRepository;
        this.timezoneGateway = timezoneGateway;
        this.distanciaUseCase = distanciaUseCase;
        this.eventsGateway = eventsGateway;
        this.tarifaUseCase = tarifaUseCase;
    }

    public Mono<UbicacionIp> obtenerUbicacionIp(String ip) {
        return ubicacionIpGateway.obtenerUbicacionIp(ip)
                .flatMap(ubicacionIp -> monedaRepository.obtenerMoneda(ubicacionIp.getCodigoISO())
                        .flatMap(moneda -> armarTarifa(moneda.getCodigoMoneda())
                                .flatMap(tarifa -> obtenerHorariosPorPais(ubicacionIp.getCodigoISO())
                                        .map(horarios -> construirUbicacionIp(ubicacionIp, moneda, tarifa, horarios))))
                        .flatMap(ubicacionIp1 -> distanciaUseCase.guardarDistancia(
                                Distancia.builder()
                                        .codigoIso(ubicacionIp1.getCodigoISO())
                                        .distanciaABuenosAires(Double.parseDouble(ubicacionIp1.getDistanciaEstimada().replace(" kms", "")))
                                        .build()).thenReturn(ubicacionIp1))
                        .flatMap(ubicacionIp1 -> eventsGateway.emit(
                                Invocacion.builder()
                                        .codigoIso(ubicacionIp1.getCodigoISO())
                                        .fechaCreacion(LocalDateTime.now())
                                        .build()
                        ).thenReturn(ubicacionIp1))
                );
    }


    private Mono<String> armarTarifa(String moneda) {
        return tarifaUseCase.obtenerTarifas()
                .map(rate -> String.format(" (1 %s = %s USD)",moneda, rate.getRates().get(moneda)));
    }

    private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

    private Mono<List<String>> obtenerHorariosPorPais(String codioIso) {

        return timezoneGateway.obtenerTimeZones(codioIso)
                .map(Timezone::getTimezones)
                .map(zona -> zona.stream()
                        .map(this::calcularHoraPorZonaHoraria)
                        .collect(Collectors.toList()));
    }


    private String calcularHoraPorZonaHoraria(String timezone) {

        ZoneOffset offset;
        if ("UTC".equals(timezone)) {
            offset = ZoneOffset.UTC;
        } else {
            offset = ZoneOffset.of(timezone.replace("UTC", "").replace(":", ""));
        }

        ZonedDateTime horaActual = ZonedDateTime.now(offset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return horaActual.format(formatter);
    }

    private UbicacionIp construirUbicacionIp(UbicacionIp ubicacionIp, Moneda moneda, String tarifa, List<String> horarios) {
        return UbicacionIp.builder()
                .ip(ubicacionIp.getIp())
                .pais(ubicacionIp.getPais())
                .codigoISO(ubicacionIp.getCodigoISO())
                .latitud(ubicacionIp.getLatitud())
                .longitud(ubicacionIp.getLongitud())
                .idiomas(ubicacionIp.getIdiomas())
                .moneda(moneda.getCodigoMoneda().concat(tarifa))
                .distanciaEstimada(String.format("%.0f kms", distance(LATITUD_BUENOS_AIRES, LONGITUD_BUENOS_AIRES,
                        ubicacionIp.getLatitud(), ubicacionIp.getLongitud(), "K")))
                .horarios(horarios)
                .build();
    }
}

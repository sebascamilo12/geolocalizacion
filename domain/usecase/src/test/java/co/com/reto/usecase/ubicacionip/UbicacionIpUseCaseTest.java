package co.com.reto.usecase.ubicacionip;

import co.com.reto.model.common.EventsGateway;
import co.com.reto.model.distancia.Distancia;
import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.model.moneda.Moneda;
import co.com.reto.model.moneda.gateways.MonedaRepository;
import co.com.reto.model.tarifa.Rate;
import co.com.reto.model.timezone.Timezone;
import co.com.reto.model.timezone.gateways.TimezoneGateway;
import co.com.reto.model.ubicacionip.UbicacionIp;
import co.com.reto.model.ubicacionip.gateways.UbicacionIpGateway;
import co.com.reto.usecase.distancia.DistanciaUseCase;
import co.com.reto.usecase.tarifa.TarifaUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UbicacionIpUseCaseTest {

    @InjectMocks
    private UbicacionIpUseCase ubicacionIpUseCase;
    @Mock
    private UbicacionIpGateway ubicacionIpGateway;
    @Mock
    private MonedaRepository monedaRepository;
    @Mock
    private TimezoneGateway timezoneGateway;
    @Mock
    private EventsGateway eventsGateway;
    @Mock
    private TarifaUseCase tarifaUseCase;
    @Mock
    private DistanciaUseCase distanciaUseCase;
    private List<String> idiomas = new ArrayList<>();
    private List<String> horarios = new ArrayList<>();
    private List<String> zonasHorarias = new ArrayList<>();
    private Moneda moneda = new Moneda("COP", "CO");
    private Distancia distancia = new Distancia("CO", 7890.);
    private Timezone timezone = new Timezone(new ArrayList<>());
    private Rate rate = new Rate();
    private HashMap<String, Double> tarifa = new HashMap<>();

    @Test
    void obtenerUbicacionIp() {

        idiomas.add("Espanol");
        zonasHorarias.add("UTC-05:00");
        timezone.setTimezones(zonasHorarias);
        tarifa.put("COP", 4050.4);
        rate.setRates(tarifa);

        UbicacionIp ubicacionIp =
                new UbicacionIp("10.10.10.10", "Colombia", "CO", 4578.0,
                        8970.5, idiomas, "COP", "7890 kms", horarios);

        when(ubicacionIpGateway.obtenerUbicacionIp(anyString())).thenReturn(Mono.just(ubicacionIp));
        when(monedaRepository.obtenerMoneda(anyString())).thenReturn(Mono.just(moneda));
        when(distanciaUseCase.guardarDistancia(any(Distancia.class))).thenReturn(Mono.just(distancia));
        when(eventsGateway.emit(any(Invocacion.class))).thenReturn(Mono.empty());
        when(timezoneGateway.obtenerTimeZones(anyString())).thenReturn(Mono.just(timezone));
        when(tarifaUseCase.obtenerTarifas()).thenReturn(Mono.just(rate));

        StepVerifier.create(ubicacionIpUseCase.obtenerUbicacionIp("10.10.10.10"))
                .consumeNextWith(resultado -> {
                    Assertions.assertEquals("CO", resultado.getCodigoISO());
                    Assertions.assertEquals("Colombia", resultado.getPais());
                    Assertions.assertEquals("COP (1 COP = 4050.4 USD)", resultado.getMoneda());
                })
                .expectComplete()
                .verify();

        verify(ubicacionIpGateway, times(1)).obtenerUbicacionIp(anyString());
        verify(monedaRepository, times(1)).obtenerMoneda(anyString());
        verify(timezoneGateway, times(1)).obtenerTimeZones(anyString());
        verify(tarifaUseCase, times(1)).obtenerTarifas();
    }
}
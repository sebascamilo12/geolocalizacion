package co.com.reto.r2dbcrepository.distancia;

import co.com.reto.r2dbcrepository.distancia.model.DistanciaData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MyReactiveDistanciaRepository extends ReactiveCrudRepository<DistanciaData, String>, ReactiveQueryByExampleExecutor<DistanciaData> {

    @Query("SELECT * FROM geolocalizacion.distancia " +
            "WHERE CODIGO_ISO = :codigoIso ")
    Mono<DistanciaData> obtenerDistancia(String codigoIso);

    @Query("SELECT * FROM geolocalizacion.distancia ORDER BY DISTANCIA_A_BUENOSAIRES")
    Flux<DistanciaData> obtenerDistancias();

    @Query("SELECT SUM(d.distancia_a_buenosaires)/ COUNT(*) " +
            "FROM geolocalizacion.invocaciones i, geolocalizacion.distancia d " +
            "WHERE i.codigo_iso = d.codigo_iso")
    Mono<Double> obtenerPromedioDistancia();

}

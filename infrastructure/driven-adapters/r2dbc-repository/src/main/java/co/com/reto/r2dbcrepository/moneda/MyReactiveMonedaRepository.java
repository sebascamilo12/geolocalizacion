package co.com.reto.r2dbcrepository.moneda;

import co.com.reto.r2dbcrepository.moneda.model.MonedaData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

// TODO: This file is just an example, you should delete or modify it
public interface MyReactiveMonedaRepository extends ReactiveCrudRepository<MonedaData, String>, ReactiveQueryByExampleExecutor<MonedaData> {

    @Query("SELECT * FROM geolocalizacion.moneda " +
            "WHERE CODIGO_ISO = :codigoIso ")
    Mono<MonedaData> findByCodigoIso(String codigoIso);

}

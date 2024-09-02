package co.com.reto.r2dbcrepository.moneda;

import co.com.reto.model.moneda.Moneda;
import co.com.reto.model.moneda.gateways.MonedaRepository;
import co.com.reto.r2dbcrepository.helper.ReactiveAdapterOperations;
import co.com.reto.r2dbcrepository.moneda.model.MonedaData;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MyReactiveRepositoryMonedaAdapter extends ReactiveAdapterOperations<Moneda, MonedaData, String, MyReactiveMonedaRepository>
        implements MonedaRepository {

    private static final Logger logger = LoggerFactory.getLogger(MyReactiveRepositoryMonedaAdapter.class);

    public MyReactiveRepositoryMonedaAdapter(MyReactiveMonedaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Moneda.class));
    }

    @Override
    @Cacheable("moneda")
    public Mono<Moneda> obtenerMoneda(String codigoIso) {
        logger.info("Consumiendo BD obtener moneda");
        return repository.findByCodigoIso(codigoIso)
                .map(monedaData -> {
                    logger.info("Consulta finalizada");
                    return mapper.map(monedaData, Moneda.class);
                });
    }

}

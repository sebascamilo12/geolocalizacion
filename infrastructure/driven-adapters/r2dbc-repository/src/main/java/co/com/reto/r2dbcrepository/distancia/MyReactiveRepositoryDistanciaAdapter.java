package co.com.reto.r2dbcrepository.distancia;


import co.com.reto.model.distancia.Distancia;
import co.com.reto.model.distancia.gateways.DistanciaRepository;
import co.com.reto.r2dbcrepository.distancia.model.DistanciaData;
import co.com.reto.r2dbcrepository.helper.ReactiveAdapterOperations;;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public class MyReactiveRepositoryDistanciaAdapter extends ReactiveAdapterOperations<Distancia, DistanciaData, String, MyReactiveDistanciaRepository>
        implements DistanciaRepository {

    public MyReactiveRepositoryDistanciaAdapter(MyReactiveDistanciaRepository repository, ObjectMapper mapper) {
           super(repository, mapper, d -> mapper.map(d, Distancia.class));
    }

    @Override
    public Mono<Distancia> guardarDistancia(Distancia distancia) {
        return repository.save(mapper.map(distancia, DistanciaData.class))
                .map(distanciaData -> mapper.map(distanciaData, Distancia.class));
    }

    @Override
    public Mono<Distancia> obtenerDistancia(String codigoIso) {
        return repository.obtenerDistancia(codigoIso)
                .map(distanciaData -> mapper.map(distanciaData, Distancia.class));
    }

    @Override
    public Flux<Distancia> obtenerDistancias() {
        return repository.obtenerDistancias()
                .map(distanciaData -> mapper.map(distanciaData, Distancia.class));
    }

    @Override
    public Mono<Double> obtenerPromedioDistancia() {
        return repository.obtenerPromedioDistancia();
    }
}

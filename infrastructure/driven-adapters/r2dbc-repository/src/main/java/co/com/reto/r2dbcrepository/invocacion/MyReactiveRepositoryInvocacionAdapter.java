package co.com.reto.r2dbcrepository.invocacion;


import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.model.invocacion.gateways.InvocacionRepository;
import co.com.reto.r2dbcrepository.helper.ReactiveAdapterOperations;
import co.com.reto.r2dbcrepository.invocacion.model.InvocacionData;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public class MyReactiveRepositoryInvocacionAdapter extends ReactiveAdapterOperations<Invocacion, InvocacionData, String, MyReactiveInvocacionRepository>
        implements InvocacionRepository {

    public MyReactiveRepositoryInvocacionAdapter(MyReactiveInvocacionRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Invocacion.class));

    }

    @Override
    public Mono<Invocacion> guardarInvocacion(Invocacion invocacion) {
        return repository.save(mapper.map(invocacion, InvocacionData.class))
                .map(invocacionData -> mapper.map(invocacionData, Invocacion.class));
    }
}

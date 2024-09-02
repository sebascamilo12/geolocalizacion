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
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Invocacion.class));

    }

    @Override
    public Mono<Invocacion> guardarInvocacion(Invocacion invocacion) {
        return repository.save(mapper.map(invocacion, InvocacionData.class))
                .map(invocacionData -> mapper.map(invocacionData, Invocacion.class));
    }
}

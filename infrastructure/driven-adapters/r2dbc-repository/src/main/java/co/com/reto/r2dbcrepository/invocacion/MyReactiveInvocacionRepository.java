package co.com.reto.r2dbcrepository.invocacion;

import co.com.reto.r2dbcrepository.invocacion.model.InvocacionData;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MyReactiveInvocacionRepository extends ReactiveCrudRepository<InvocacionData, String>, ReactiveQueryByExampleExecutor<InvocacionData> {

}

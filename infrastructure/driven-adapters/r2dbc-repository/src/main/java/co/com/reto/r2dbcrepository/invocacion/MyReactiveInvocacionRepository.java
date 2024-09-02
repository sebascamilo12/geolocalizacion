package co.com.reto.r2dbcrepository.invocacion;

import co.com.reto.r2dbcrepository.invocacion.model.InvocacionData;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;



// TODO: This file is just an example, you should delete or modify it
public interface MyReactiveInvocacionRepository extends ReactiveCrudRepository<InvocacionData, String>, ReactiveQueryByExampleExecutor<InvocacionData> {

}

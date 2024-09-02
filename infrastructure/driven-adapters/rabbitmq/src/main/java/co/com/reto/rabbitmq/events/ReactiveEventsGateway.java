package co.com.reto.rabbitmq.events;

import co.com.reto.model.common.EventsGateway;
import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.rabbitmq.RabbitSender;
import co.com.reto.rabbitmq.dto.InvocacionDTO;
import co.com.reto.rabbitmq.mapper.InvocacionMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveEventsGateway implements EventsGateway {

    private final RabbitSender rabbitSender;
    private final InvocacionMapper invocacionMapper;

    public ReactiveEventsGateway(RabbitSender rabbitSender, InvocacionMapper invocacionMapper) {
        this.rabbitSender = rabbitSender;
        this.invocacionMapper = invocacionMapper;
    }

    @Override
    public Mono<Void> emit(Invocacion invocacion) {
        Mono<InvocacionDTO> invocacionDTO = Mono.just(invocacionMapper.domainToDTO(invocacion));
        return rabbitSender.createSender(invocacionDTO).then();

    }
}

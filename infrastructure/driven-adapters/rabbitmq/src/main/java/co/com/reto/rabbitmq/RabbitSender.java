package co.com.reto.rabbitmq;

import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.rabbitmq.dto.InvocacionDTO;
import co.com.reto.rabbitmq.mapper.InvocacionMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.Sender;

@Service
public class RabbitSender {

    private final Sender sender;
    private InvocacionMapper invocacionMapper;
    private static final String queue = "mensajes";

    private static final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    public RabbitSender(Sender sender, InvocacionMapper invocacionMapper) {
        this.sender = sender;
        this.invocacionMapper = invocacionMapper;
    }

    public Mono<Invocacion> createSender(Mono<InvocacionDTO> invocacionDTO) {

        return invocacionDTO.flatMap(invocacionDTO1 -> {
            Invocacion invocacion = invocacionMapper.dtoToDomain(invocacionDTO1);


            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            String json;
            try {
                json = mapper.writeValueAsString(invocacion);
                byte[] orderSerialized = SerializationUtils.serialize(json);
                Flux<OutboundMessage> outbound = Flux.just(new OutboundMessage("", queue, orderSerialized));
                sender.sendWithPublishConfirms(outbound)
                        .doOnError(e -> logger.error("Envio Fallido", e))
                        .subscribe(m ->
                                logger.info("Mensaje enviado...")
                        );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return Mono.just(invocacion);

        });
    }
}

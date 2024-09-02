package co.com.reto.rabbitmqreceiver;

import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.usecase.invocacion.InvocacionUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Receiver;

import java.io.IOException;


@Service
public class RabbitReceiver {
    @Autowired
    @Qualifier("receiverConnectionMono")
    private Mono<Connection> connectionMono;
    private final Receiver receiver;
    private final InvocacionUseCase invocacionUseCase;
    private static final String RECEIVER_QUEUE = "mensajes";
    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

    public RabbitReceiver(Receiver receiver, InvocacionUseCase invocacionUseCase) {
        this.receiver = receiver;
        this.invocacionUseCase = invocacionUseCase;
    }

    @PostConstruct
    private void init() {
        connectionMono.map(connection -> {
            try {
                logger.info("Creacion cola");
                Channel channel = connection.createChannel();
                channel.queueDeclare(RECEIVER_QUEUE, false, false, false, null);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            return Mono.empty();
        }).subscribe(creacionCola ->
                logger.info("Cola declarada"));
        consume();
    }

    public Disposable consume() {
        return receiver.consumeAutoAck(RECEIVER_QUEUE).subscribe(m -> {

            String json = (String) SerializationUtils.deserialize(m.getBody());
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();

            Invocacion invocacion;

            try {
                invocacion = mapper.readValue(json, Invocacion.class);
                invocacionUseCase.guardarInvocacion(invocacion)
                        .subscribe(invocacionGuardada ->
                                logger.info("Guardado en BD: " + invocacionGuardada.getCodigoIso()));

                logger.info(json);
                logger.info(invocacion.getCodigoIso() + " " + invocacion.getFechaCreacion());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

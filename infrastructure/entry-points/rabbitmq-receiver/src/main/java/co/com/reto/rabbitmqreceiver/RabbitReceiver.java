package co.com.reto.rabbitmqreceiver;

import co.com.reto.model.invocacion.Invocacion;
import co.com.reto.usecase.invocacion.InvocacionUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import jakarta.annotation.PostConstruct;
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
    private static final String RECEIVER_QUEUE = "mensajes";
    @Autowired
    @Qualifier("receiverConnectionMono")
    private Mono<Connection> connectionMono;

    private final Receiver receiver;
    private final InvocacionUseCase invocacionUseCase;

    public RabbitReceiver(Receiver receiver, InvocacionUseCase invocacionUseCase) {
        this.receiver = receiver;
        this.invocacionUseCase = invocacionUseCase;
    }
    @PostConstruct
    private void init()  {
         connectionMono.map(connection -> {
             try {
                 System.out.println("Creando cola");
                 Channel channel = connection.createChannel();
                 channel.queueDeclare("mensajes", false, false, false, null);

             } catch (IOException ex) {
                 throw new RuntimeException(ex);
             }

             return Mono.empty();
         }).subscribe(creacionCola ->{
             System.out.println("Queue declared");
         });
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
                    .subscribe(invocacionGuardada ->{
                        System.out.println("Guardado en BD: " + invocacionGuardada.getCodigoIso());
                    });
            System.out.println(json.toString());
            System.out.println(invocacion.getCodigoIso() + " " + invocacion.getFechaCreacion());

        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}
}

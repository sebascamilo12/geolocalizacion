package co.com.reto.rabbitmqreceiver.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.ReceiverOptions;

@Configuration
public class RabbitMQReceiverConfig {
    @Value("${msgbroker.host}")
    private String host;
    @Value("${msgbroker.username}")
    private String username;
    @Value("${msgbroker.password}")
    private String password;

    @Bean(name = "receiverConnectionMono")
    Mono<Connection> receiverConnectionMono() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.useNio();
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return Mono.fromCallable(() -> connectionFactory.newConnection("receiver-sms-rabbitmq")).cache();
    }

    @Bean
    public ReceiverOptions receiverOptions(@Qualifier("receiverConnectionMono") Mono<Connection> connectionMono) {
        return new ReceiverOptions()
                .connectionMono(connectionMono);
    }

    @Bean
    Receiver receiver(ReceiverOptions receiverOptions) {
        return RabbitFlux.createReceiver(receiverOptions);
    }
}

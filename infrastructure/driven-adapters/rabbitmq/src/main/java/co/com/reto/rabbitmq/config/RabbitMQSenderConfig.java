package co.com.reto.rabbitmq.config;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Sender;
import reactor.rabbitmq.SenderOptions;

@Configuration
public class RabbitMQSenderConfig {

    @Value("${msgbroker.host}")
    private String host;
    @Value("${msgbroker.username}")
    private String username;
    @Value("${msgbroker.password}")
    private String password;
    @Bean(name = "senderConnectionMono")
    Mono<Connection> senderConnectionMono() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.useNio();
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return Mono.fromCallable(() -> connectionFactory.newConnection("sender-sms-rabbitmq")).cache();
    }

    @Bean
    public SenderOptions senderOptions(@Qualifier("senderConnectionMono") Mono<Connection> connectionMono) {
        return new SenderOptions()
                .connectionMono(connectionMono)
                .resourceManagementScheduler(Schedulers.boundedElastic());
    }

    @Bean
    public Sender sender(SenderOptions senderOptions) {
        return RabbitFlux.createSender(senderOptions);
    }



}

package co.com.reto.r2dbcrepository.config;

import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MySQLConnectionPool {
    // TODO: change pool connection properties based on your resources.
    public static final int INITIAL_SIZE = 12;
    public static final int MAX_SIZE = 15;
    public static final int MAX_IDLE_TIME = 30;

	@Bean
	public ConnectionPool getConnectionConfig() {
        // TODO: change these properties for yours
		MysqlConnectionProperties pgProperties = new MysqlConnectionProperties();
		pgProperties.setDatabase("geolocalizacion");
		pgProperties.setHost("mysql");
		pgProperties.setPort(3306);
		pgProperties.setUsername("root");
		pgProperties.setPassword("root");

		return buildConnectionConfiguration(pgProperties);
	}

	private ConnectionPool buildConnectionConfiguration(MysqlConnectionProperties properties) {
		MySqlConnectionConfiguration dbConfiguration = MySqlConnectionConfiguration .builder()
				.host(properties.getHost())
				.port(properties.getPort())
				.database(properties.getDatabase())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();

        ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder()
                .connectionFactory(MySqlConnectionFactory.from(dbConfiguration))
                .name("api-mysql-connection-pool")
                .initialSize(INITIAL_SIZE)
                .maxSize(MAX_SIZE)
                .maxIdleTime(Duration.ofMinutes(MAX_IDLE_TIME))
                .validationQuery("SELECT 1")
                .build();

		return new ConnectionPool(poolConfiguration);
	}
}

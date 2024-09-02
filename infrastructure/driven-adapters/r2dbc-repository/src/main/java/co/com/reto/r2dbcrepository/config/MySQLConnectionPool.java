package co.com.reto.r2dbcrepository.config;

import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MySQLConnectionPool {

    public static final int INITIAL_SIZE = 12;
    public static final int MAX_SIZE = 15;
    public static final int MAX_IDLE_TIME = 30;
	@Value("${datasource.r2dbc.host}")
	private String host;
	@Value("${datasource.r2dbc.port}")
	private Integer port;
	@Value("${datasource.r2dbc.username}")
	private String userName;
	@Value("${datasource.r2dbc.password}")
	private String password;

	@Bean
	public ConnectionPool getConnectionConfig() {

		MysqlConnectionProperties pgProperties = new MysqlConnectionProperties();
		pgProperties.setDatabase("geolocalizacion");
		pgProperties.setHost(host);
		pgProperties.setPort(port);
		pgProperties.setUsername(userName);
		pgProperties.setPassword(password);

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

package sequia_integra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@EnableReactiveMongoRepositories
@SpringBootApplication
public class SequiaIntegraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SequiaIntegraApplication.class, args);
	}

}

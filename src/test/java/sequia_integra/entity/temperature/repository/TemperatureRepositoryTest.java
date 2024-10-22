package sequia_integra.entity.temperature.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;
import reactor.test.StepVerifier;

@DataMongoTest
class TemperatureRepositoryTest {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @BeforeEach
    void setUp() {
        temperatureRepository.deleteAll().block();
    }

    @Test
    void testFindByYear() {
        Temperature temperature = new Temperature("1", 2023, 10, 25.5);
        temperatureRepository.save(temperature).block();

        Mono<Temperature> foundTemperature = temperatureRepository.findByYear(2023);

        StepVerifier.create(foundTemperature)
                .expectNextMatches(temp -> temp.getYear() == 2023)
                .verifyComplete();
    }

    @Test
    void testFindByMonth() {
        Temperature temperature = new Temperature("1", 2023, 10, 25.5);
        temperatureRepository.save(temperature).block();

        Mono<Temperature> foundTemperature = temperatureRepository.findByMonth(10);

        StepVerifier.create(foundTemperature)
                .expectNextMatches(temp -> temp.getMonth() == 10)
                .verifyComplete();
    }

    @Test
    void testFindByYearAndMonth() {
        Temperature temperature = new Temperature("1", 2023, 10, 25.5);
        temperatureRepository.save(temperature).block();

        Mono<Temperature> foundTemperature = temperatureRepository.findByYearAndMonth(2023, 10);

        StepVerifier.create(foundTemperature)
                .expectNextMatches(temp -> temp.getYear() == 2023 && temp.getMonth() == 10)
                .verifyComplete();
    }
}
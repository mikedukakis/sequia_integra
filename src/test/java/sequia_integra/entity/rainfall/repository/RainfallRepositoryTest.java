package sequia_integra.entity.rainfall.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sequia_integra.entity.rainfall.domain.RainfallEntity;

@DataMongoTest
class RainfallRepositoryTest {

    @Autowired
    private RainfallRepository rainfallRepository;

    @BeforeEach
    void setUp() {
        rainfallRepository.deleteAll().block();
    }

    @Test
    void testFindByMonthAndYear() {
        RainfallEntity rainfallEntity = new RainfallEntity();
        rainfallEntity.setId("1");
        rainfallEntity.setMonth(10);
        rainfallEntity.setYear(2023);
        rainfallEntity.setPrecipitation(100.0);
        rainfallRepository.save(rainfallEntity).block();

        Mono<RainfallEntity> foundRainfall = rainfallRepository.findByMonthAndYear(10, 2023);

        StepVerifier.create(foundRainfall)
                .expectNextMatches(rainfall -> rainfall.getMonth() == 10 && rainfall.getYear() == 2023)
                .verifyComplete();
    }
}
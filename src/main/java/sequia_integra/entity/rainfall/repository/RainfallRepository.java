package sequia_integra.entity.rainfall.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.rainfall.domain.RainfallEntity;
import sequia_integra.entity.temperature.domain.Temperature;

@Repository
public interface RainfallRepository extends ReactiveMongoRepository<RainfallEntity, String> {
    Mono<RainfallEntity> findByMonthAndYear(int month, int year);
}

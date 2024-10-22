package sequia_integra.entity.rainfall.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.rainfall.domain.RainfallEntity;

@Repository
public interface RainfallRepository extends ReactiveMongoRepository<RainfallEntity, Integer> {
    Mono<RainfallEntity> findByMonthAndYear(int month, int year);

    @Override
    Flux<RainfallEntity> findAll();
}

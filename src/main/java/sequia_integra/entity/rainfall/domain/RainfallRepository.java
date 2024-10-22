package sequia_integra.entity.rainfall.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RainfallRepository extends ReactiveMongoRepository<RainfallEntity, Integer> {
    Mono<RainfallEntity> findByMonthAndYear(int Mes, int Any);

    @Override
    Flux<RainfallEntity> findAll();
}

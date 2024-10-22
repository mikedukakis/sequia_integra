package sequia_integra.entity.rainfall.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RainfallRepository extends ReactiveMongoRepository<RainfallEntity, String> {
    Mono<RainfallEntity> findById(String id);
}

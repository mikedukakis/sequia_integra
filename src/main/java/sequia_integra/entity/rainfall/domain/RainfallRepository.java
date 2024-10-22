package sequia_integra.entity.rainfall.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RainfallRepository extends ReactiveMongoRepository<Rainfall, String> {
    Mono<Rainfall> findById(String id);
}

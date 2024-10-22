package sequia_integra.entity.temperature.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;

public interface TemperatureRepository extends ReactiveMongoRepository<Temperature, String> {


    public Mono<Temperature> findByYear(int year);

    public Mono<Temperature> findByMonth(int month);

    public Mono<Temperature> findByYearAndMonth(int year, int month);

}

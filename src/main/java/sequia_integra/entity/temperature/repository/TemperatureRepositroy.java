package sequia_integra.entity.temperature.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;

public interface TemperatureRepositroy extends ReactiveMongoRepository<Temperature, String> {

    public Mono<Double> findByYear(int year);

    public Mono<Double> findByMonth(int month);

    public Mono<Double> findByYearAndMonth(int year, int month);

}

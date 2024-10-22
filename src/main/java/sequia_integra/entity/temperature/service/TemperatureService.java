package sequia_integra.entity.temperature.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.TemperatureDto;

public interface TemperatureService {

    public Mono<Double> getTempMonth(int year, int month);

    public Mono<Double> getTempMonthRandomYear(int month);

    public Flux<TemperatureDto> getHistoric();

}

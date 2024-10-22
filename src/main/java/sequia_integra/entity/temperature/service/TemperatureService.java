package sequia_integra.entity.temperature.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;
import sequia_integra.entity.temperature.domain.TemperatureDto;
import sequia_integra.entity.temperature.domain.VulnerabilityDto;

public interface TemperatureService {

    public Mono<Temperature> getTempMonth(int year, int month);

    public Mono<Temperature> getTempMonthRandomYear(int month);

    public Flux<TemperatureDto> getHistoric();

    public Flux<VulnerabilityDto> getVulnerabilityIndex();


}

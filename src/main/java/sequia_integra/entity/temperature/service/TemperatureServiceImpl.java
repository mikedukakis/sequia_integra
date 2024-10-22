package sequia_integra.entity.temperature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;
import sequia_integra.entity.temperature.domain.TemperatureDto;
import sequia_integra.entity.temperature.repository.TemperatureRepositroy;

import java.util.HashMap;
import java.util.Map;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    private final TemperatureRepositroy temperatureRepositroy;

    @Autowired
    public TemperatureServiceImpl(TemperatureRepositroy temperatureRepositroy) {
        this.temperatureRepositroy = temperatureRepositroy;
    }

    @Override
    public Mono<Double> getTempMonth(int year, int month) {
        return temperatureRepositroy.findByYearAndMonth(year, month);
    }

    @Override
    public Mono<Double> getTempMonthRandomYear(int month) {
        int randomYear = 1900 + (int) (Math.random() * (2023 - 1900 + 1));
        return temperatureRepositroy.findByYearAndMonth(randomYear, month);
    }

    @Override
    public Flux<TemperatureDto> getHistoric() {
        return temperatureRepositroy.findAll()
                .collectList()
                .flatMapMany(temperatures -> {
                    Map<Integer, Double> yearTemps = new HashMap<>();
                    Map<Integer, Integer> yearCounts = new HashMap<>();

                    for (Temperature temp : temperatures) {
                        int year = temp.getYear();
                        if (year >= 1950 && year <= 2023) {
                            yearTemps.put(year, yearTemps.getOrDefault(year, 0.0) + temp.getTemperature());
                            yearCounts.put(year, yearCounts.getOrDefault(year, 0) + 1);
                        }
                    }

                    return Flux.fromStream(yearTemps.keySet().stream()
                                    .map(year -> new TemperatureDto(year, yearTemps.get(year) / yearCounts.get(year))))
                            .onErrorResume(e -> Flux.error(new RuntimeException("Error calculating historic temperatures", e)));
                });
    }
}
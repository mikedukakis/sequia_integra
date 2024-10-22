package sequia_integra.entity.temperature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;
import sequia_integra.entity.temperature.repository.TemperatureRepositroy;

import java.util.HashMap;

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
    public Flux<Double> getHistoric() {
        return temperatureRepositroy.findAll()
                .collectList()
                .flatMapMany(temperatures -> {
                    HashMap<Integer, Double> monthTemps = new HashMap<>();
                    HashMap<Integer, Integer> monthCounts = new HashMap<>();

                    for (Temperature temp : temperatures) {
                        int month = temp.getMonth();
                        monthTemps.put(month, monthTemps.getOrDefault(month, 0.0) + temp.getTemperature());
                        monthCounts.put(month, monthCounts.getOrDefault(month, 0) + 1);
                    }

                    return Flux.fromStream(monthTemps.keySet().stream()
                                    .map(month -> monthTemps.get(month) / monthCounts.get(month)))
                            .onErrorResume(e -> Flux.error(new RuntimeException("Error calculating historic temperatures", e)));
                });
    }


}
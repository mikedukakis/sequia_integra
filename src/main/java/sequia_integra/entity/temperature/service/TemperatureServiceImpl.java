package sequia_integra.entity.temperature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;
import sequia_integra.entity.temperature.domain.TemperatureDto;
import sequia_integra.entity.temperature.domain.VulnerabilityDto;
import sequia_integra.entity.temperature.exception.CalculatingHistoricException;
import sequia_integra.entity.temperature.exception.CalculatingVulnerabilityException;
import sequia_integra.entity.temperature.repository.TemperatureRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    private final TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureServiceImpl(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public Mono<Temperature> getTempMonth(int year, int month) {
        return temperatureRepository.findByYearAndMonth(year, month);
    }

    @Override
    public Mono<Temperature> getTempMonthRandomYear(int month) {
        int randomYear = 1900 + (int) (Math.random() * (2023 - 1900 + 1));
        return temperatureRepository.findByYearAndMonth(randomYear, month);
    }

    @Override
    public Flux<TemperatureDto> getHistoric() {
        return temperatureRepository.findAll()
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
                            .onErrorResume(e -> Flux.error(new CalculatingHistoricException("Error calculating historic temperatures")));
                });
    }

    @Override
    public Flux<VulnerabilityDto> getVulnerabilityIndex() {
        return temperatureRepository.findAll()
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

                    Map<Integer, Double> avgYearTemps = yearTemps.entrySet().stream()
                            .collect(Collectors.toMap(Map.Entry::getKey,
                                    entry -> entry.getValue() / yearCounts.get(entry.getKey())));

                    double maxTemp = avgYearTemps.values().stream().max(Double::compare).orElse(0.0);
                    double minTemp = avgYearTemps.values().stream().min(Double::compare).orElse(0.0);


                    return Flux.fromStream(avgYearTemps.entrySet().stream()
                                    .map(entry -> {
                                        int year = entry.getKey();
                                        double avgTemp = entry.getValue();

                                        double vulnerabilityIndex = 1 + ((avgTemp - minTemp) / (maxTemp - minTemp)) * (10 - 1);
                                        return new VulnerabilityDto(year, avgTemp, vulnerabilityIndex);
                                    }))
                            .onErrorResume(e -> Flux.error(new CalculatingVulnerabilityException("Error calculating vulnerability index")));
                });
    }
}
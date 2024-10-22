package sequia_integra.entity.temperature.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;
import sequia_integra.entity.temperature.domain.TemperatureDto;
import sequia_integra.entity.temperature.service.TemperatureService;

@RequestMapping("/api/temp")
@RestController
public class TemperatureController {

    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/month/{year}/{month}")
    public ResponseEntity<Mono<Temperature>> getTempMonth(@PathVariable int year, @PathVariable int month) {
        return ResponseEntity.ok(temperatureService.getTempMonth(year, month));
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<Mono<Temperature>> getTempMonthRandomYear(@PathVariable int month) {
        return ResponseEntity.ok(temperatureService.getTempMonthRandomYear(month));
    }

    @GetMapping("/historic")
    public ResponseEntity<Flux<TemperatureDto>> getHistoric() {
        return ResponseEntity.ok(temperatureService.getHistoric());
    }
}
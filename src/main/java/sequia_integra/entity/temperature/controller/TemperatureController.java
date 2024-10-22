package sequia_integra.entity.temperature.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import sequia_integra.entity.temperature.service.TemperatureService;

@RequestMapping("/api/temp")
@RestController
public class TemperatureController {

    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/month/year")
    public ResponseEntity<Double> getTempMonth(int year, int month) {
        return ResponseEntity.ok(temperatureService.getTempMonth(year, month).block());
    }

    @GetMapping("/month/")
    public ResponseEntity<Double> getTempMonthRandomYear(int month) {
        return ResponseEntity.ok(temperatureService.getTempMonthRandomYear(month).block());
    }

    @GetMapping("/historic")
    public ResponseEntity<Flux<Double>> getHistoric() {
        return ResponseEntity.ok(temperatureService.getHistoric());
    }


}

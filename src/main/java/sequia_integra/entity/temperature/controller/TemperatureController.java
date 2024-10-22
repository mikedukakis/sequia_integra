package sequia_integra.entity.temperature.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;
import sequia_integra.entity.temperature.domain.TemperatureDto;
import sequia_integra.entity.temperature.service.TemperatureService;

@RestController
@RequestMapping("/api/temp")
public class TemperatureController {

    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @Operation(summary = "Get temperature by year and month")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temperature found"),
            @ApiResponse(responseCode = "404", description = "Temperature not found")
    })
    @GetMapping("/month/{year}/{month}")
    public Mono<ResponseEntity<Temperature>> getTempMonth(@PathVariable int year, @PathVariable int month) {
        return temperatureService.getTempMonth(year, month)
                .map(temp -> ResponseEntity.ok(temp))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Get temperature by month and random year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temperature found"),
            @ApiResponse(responseCode = "404", description = "Temperature not found")
    })
    @GetMapping("/month/{month}")
    public Mono<ResponseEntity<Temperature>> getTempMonthRandomYear(@PathVariable int month) {
        return temperatureService.getTempMonthRandomYear(month)
                .map(temp -> ResponseEntity.ok(temp))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Get historic temperatures")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found historic temperatures"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/historic")
    public ResponseEntity<Flux<TemperatureDto>> getHistoric() {
        return ResponseEntity.ok(temperatureService.getHistoric());
    }

}
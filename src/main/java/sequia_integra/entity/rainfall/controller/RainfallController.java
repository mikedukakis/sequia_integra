package sequia_integra.entity.rainfall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.rainfall.domain.RainfallEntity;
import sequia_integra.entity.rainfall.service.RainfallService;
import sequia_integra.entity.temperature.domain.Temperature;

@Data
@RestController
@RequestMapping("/api/rainfall")
public class RainfallController {
    private final RainfallService rainfallService;

    @Operation(summary = "Rainfall for month", description = "Retrieve rainfall of a specific month/year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Details provided successfully"),
            @ApiResponse(responseCode = "404", description = "Couldn't access the details")
    })
    @GetMapping("/{month}/{year}")
    public Mono<ResponseEntity<RainfallEntity>> getRainfall(@PathVariable int month, @PathVariable int year) {
        return rainfallService.getRainfall(month, year)
                .map(rainfall -> ResponseEntity.ok(rainfall))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Rainfall for month on random year", description = "Retrieve rainfall of a specific month in a random yearyear")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Details provided successfully"),
            @ApiResponse(responseCode = "404", description = "Couldn't access the details")
    })
    @GetMapping("/month/{month}")
    public Mono<ResponseEntity<RainfallEntity>> getMonthRandomYear(@PathVariable int month) {
        return rainfallService.getMonthRandomYear(month)
                .map(rainfall -> ResponseEntity.ok(rainfall))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}

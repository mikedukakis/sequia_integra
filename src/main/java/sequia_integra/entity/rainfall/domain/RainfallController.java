package sequia_integra.entity.rainfall.domain;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@RestController
@RequestMapping("api/rainfall")
public class RainfallController {
    private final RainfallService rainfallService;

    @Operation(summary = "Rainfall for month", description "Retrieve rainfall of a specific month/year")
    @ApiResponse(responseCode = "200", description = "Details provided successfully")
    @ApiResponse(responseCode = "500", description = "Couldn't access the details")
    @GetMapping("/{month}/{year}")
    public Mono<RainfallEntity> getRainfall(@PathVariable String id) {
        return rainfallService.getRainfall(id);
    }


    @Operation(summary = "Rainfall for all months", description "Retrieve rainfall for all months of all years")
    @ApiResponse(responseCode = "200", description = "Details provided successfully")
    @ApiResponse(responseCode = "500", description = "Couldn't access the details")
    @GetMapping("/month")
    public Flux<RainfallEntity> getAllRainfall() {
        return rainfallService.getAllRainfall();
    }


}

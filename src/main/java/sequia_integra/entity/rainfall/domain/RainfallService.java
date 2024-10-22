package sequia_integra.entity.rainfall.domain;

import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@Service
public class RainfallService {
    private final RainfallRepository rainfallRepository;

    public Mono<RainfallEntity> getRainfall(String id) {
        return rainfallRepository.findById(id)
                .switchIfEmpty(Mono.error(new RainfallNotFoundException("Rainfall not found with ID: " + id)));
    }

    public Flux<RainfallEntity> getAllRainfall() {
        return rainfallRepository.findAll();
    }
}

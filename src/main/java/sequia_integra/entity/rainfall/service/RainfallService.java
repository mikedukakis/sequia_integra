package sequia_integra.entity.rainfall.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.rainfall.domain.RainfallEntity;
import sequia_integra.entity.rainfall.repository.RainfallRepository;

@Data
@Service
public class RainfallService {
    private final RainfallRepository rainfallRepository;

    public Mono<RainfallEntity> getRainfall(int month, int year) {
        return rainfallRepository.findByMonthAndYear(month, year)
                .switchIfEmpty(Mono.error(new RuntimeException("Rainfall not found by month and year" )));
    }

    public Flux<RainfallEntity> getAllRainfall() {
        return rainfallRepository.findAll();
    }
}

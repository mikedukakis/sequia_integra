package sequia_integra.entity.rainfall.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sequia_integra.entity.rainfall.domain.RainfallEntity;
import sequia_integra.entity.rainfall.repository.RainfallRepository;

@Data
@Service
public class RainfallService {
    private final RainfallRepository rainfallRepository;

    public Mono<RainfallEntity> getRainfall(int month, int year) {
        return rainfallRepository.findByMonthAndYear(month, year)
                .onErrorResume(e -> Mono.error(new RuntimeException("Month and year did not return rainfall value", e)));
    }

    public Mono<RainfallEntity> getMonthRandomYear(int month) {
        int randomYear = 1900 + (int) (Math.random() * (2023 - 1900 + 1));
        return rainfallRepository.findByMonthAndYear(month, randomYear)
                .onErrorResume(e -> Mono.error(new RuntimeException("Rainfall not found by month and year", e)));
    }
}
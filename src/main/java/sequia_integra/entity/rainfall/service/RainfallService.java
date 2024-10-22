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
                .switchIfEmpty(Mono.error(new MonthYearNotFoundException("Month and year did not returna rainfall value" )));
    }

    public Mono<RainfallEntity> getMonthRandomYear(int month) {
        int randomYear = 1900 + (int) (Math.random() * (2023 - 1900 + 1));
        return rainfallRepository.findByMonthAndYear(month, randomYear)
                .switchIfEmpty(Mono.error(new MonthYearNotFoundException("Rainfall not found by month and year" )));
    }
}

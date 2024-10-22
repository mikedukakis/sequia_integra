package sequia_integra.entity.rainfall.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sequia_integra.entity.rainfall.domain.RainfallEntity;
import sequia_integra.entity.rainfall.repository.RainfallRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RainfallServiceTest {

    @Mock
    private RainfallRepository rainfallRepository;

    @InjectMocks
    private RainfallService rainfallService;

    private final String id = "123";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRainfall() {
        RainfallEntity rainfallEntity = new RainfallEntity();
        rainfallEntity.setId("1");
        rainfallEntity.setMonth(10);
        rainfallEntity.setYear(2023);
        rainfallEntity.setPrecipitation(100.0);

        when(rainfallRepository.findByMonthAndYear(10, 2023)).thenReturn(Mono.just(rainfallEntity));

        Mono<RainfallEntity> result = rainfallService.getRainfall(10, 2023);

        assertNotNull(result);
        assertEquals(rainfallEntity, result.block());
    }



}
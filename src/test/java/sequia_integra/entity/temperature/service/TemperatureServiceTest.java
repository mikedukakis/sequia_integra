package sequia_integra.entity.temperature.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;
import sequia_integra.entity.temperature.domain.TemperatureDto;
import sequia_integra.entity.temperature.domain.VulnerabilityDto;
import sequia_integra.entity.temperature.repository.TemperatureRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemperatureServiceImplTest {

    @Mock
    private TemperatureRepository temperatureRepository;

    @InjectMocks
    private TemperatureServiceImpl temperatureService;

    private final String id = "123";
    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTempMonth() {

        Temperature temp = new Temperature(id,2022, 5, 25.0);
        when(temperatureRepository.findByYearAndMonth(2022, 5)).thenReturn(Mono.just(temp));

        Mono<Temperature> result = temperatureService.getTempMonth(2022, 5);

        assertNotNull(result);
        assertEquals(temp, result.block());
    }

    @Test
    void testGetTempMonthRandomYear() {
        Temperature temp = new Temperature(id, 2022, 5, 25.0);
        when(temperatureRepository.findByYearAndMonth(anyInt(), eq(5))).thenReturn(Mono.just(temp));

        Mono<Temperature> result = temperatureService.getTempMonthRandomYear(5);

        assertNotNull(result);
        assertEquals(temp, result.block());
    }

    @Test
    void testGetHistoric() {
        List<Temperature> temperatures = Arrays.asList(
                new Temperature(id, 2022, 1, 10.0),
                new Temperature(id, 2022, 2, 15.0)
        );
        when(temperatureRepository.findAll()).thenReturn(Flux.fromIterable(temperatures));

        Flux<TemperatureDto> result = temperatureService.getHistoric();

        List<TemperatureDto> resultList = result.collectList().block();
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertEquals(2022, resultList.get(0).getYear());
        assertEquals(12.5, resultList.get(0).getAvgTemp());
    }

    @Test
    void testGetVulnerabilityIndex() {
        List<Temperature> temperatures = Arrays.asList(
                new Temperature(id, 2022, 1, 10.0),
                new Temperature(id, 2022, 2, 15.0)
        );
        when(temperatureRepository.findAll()).thenReturn(Flux.fromIterable(temperatures));

        Flux<VulnerabilityDto> result = temperatureService.getVulnerabilityIndex();

        List<VulnerabilityDto> resultList = result.collectList().block();
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertEquals(2022, resultList.get(0).getYear());
        assertEquals(12.5, resultList.get(0).getAvgTemp());
        assertTrue(resultList.get(0).getVulnerabilityIndex() >= 1 && resultList.get(0).getVulnerabilityIndex() <= 10);
    }
}
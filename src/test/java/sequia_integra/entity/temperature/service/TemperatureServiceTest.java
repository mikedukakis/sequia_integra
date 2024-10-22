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
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemperatureServiceTest {

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
        Temperature temp = new Temperature(id, 2022, 5, 25.0);
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
        assertEquals(12.5, resultList.get(0).getAvgYearTemp());
    }

    @Test
    void testGetVulnerabilityIndex() {
        List<Temperature> temperatures = Arrays.asList(
                new Temperature(id, 1951, 1, 14.316666666666665),
                new Temperature(id, 1952, 1, 15.15),
                new Temperature(id, 1953, 1, 14.999999999999998)

        );
        when(temperatureRepository.findAll()).thenReturn(Flux.fromIterable(temperatures));

        Flux<VulnerabilityDto> result = temperatureService.getVulnerabilityIndex();

        List<VulnerabilityDto> resultList = result.collectList().block();
        assertNotNull(resultList);
        assertEquals(3, resultList.size());

        resultList.sort(Comparator.comparingInt(VulnerabilityDto::getYear));

        VulnerabilityDto dto1951 = resultList.get(0);
        assertEquals(1951, dto1951.getYear());
        assertEquals(14.316666666666665, dto1951.getAvgTemp(), 0.0001);
        assertEquals(1.0, dto1951.getVulnerabilityIndex(), 0.0001);

        VulnerabilityDto dto1952 = resultList.get(1);
        assertEquals(1952, dto1952.getYear());
        assertEquals(15.15, dto1952.getAvgTemp(), 0.0001);
        assertEquals(10.0, dto1952.getVulnerabilityIndex(), 0.0001);

        VulnerabilityDto dto1953 = resultList.get(2);
        assertEquals(1953, dto1953.getYear());
        assertEquals(14.999999999999998, dto1953.getAvgTemp(), 0.0001);
        assertEquals(8.379999999999981, dto1953.getVulnerabilityIndex(), 0.0001);
    }

}
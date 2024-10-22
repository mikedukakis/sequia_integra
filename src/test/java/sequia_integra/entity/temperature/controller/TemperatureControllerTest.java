package sequia_integra.entity.temperature.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sequia_integra.entity.temperature.domain.Temperature;
import sequia_integra.entity.temperature.domain.TemperatureDto;
import sequia_integra.entity.temperature.domain.VulnerabilityDto;
import sequia_integra.entity.temperature.service.TemperatureService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TemperatureControllerTest {

    @Mock
    private TemperatureService temperatureService;

    @InjectMocks
    private TemperatureController temperatureController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTempMonth() {
        Temperature temp = new Temperature();
        when(temperatureService.getTempMonth(2023, 10)).thenReturn(Mono.just(temp));

        ResponseEntity<Temperature> response = temperatureController.getTempMonth(2023, 10).block();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(temp, response.getBody());
    }

    @Test
    void testGetTempMonthNotFound() {
        when(temperatureService.getTempMonth(2023, 10)).thenReturn(Mono.empty());

        ResponseEntity<Temperature> response = temperatureController.getTempMonth(2023, 10).block();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetHistoric() {
        Flux<TemperatureDto> flux = Flux.just(new TemperatureDto());
        when(temperatureService.getHistoric()).thenReturn(flux);

        ResponseEntity<Flux<TemperatureDto>> response = temperatureController.getHistoric();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flux, response.getBody());
    }

    @Test
    void testGetVulnerabilityIndex() {
        Flux<VulnerabilityDto> flux = Flux.just(new VulnerabilityDto());
        when(temperatureService.getVulnerabilityIndex()).thenReturn(flux);

        ResponseEntity<Flux<VulnerabilityDto>> response = temperatureController.getVulnerabilityIndex();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flux, response.getBody());
    }
}
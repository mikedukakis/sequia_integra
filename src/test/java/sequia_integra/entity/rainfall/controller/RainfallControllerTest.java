package sequia_integra.entity.rainfall.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import sequia_integra.entity.rainfall.domain.RainfallEntity;
import sequia_integra.entity.rainfall.service.RainfallService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class RainfallControllerTest {

    @Mock
    private RainfallService rainfallService;

    @InjectMocks
    private RainfallController rainfallController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRainfall() {
        RainfallEntity rainfallEntity = new RainfallEntity();


        when(rainfallService.getRainfall(2023, 10)).thenReturn(Mono.just(rainfallEntity));

        ResponseEntity<RainfallEntity> response = rainfallController.getRainfall(10, 2023).block();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rainfallEntity, response.getBody());
    }

    @Test
    void testGetRainfallNotFound() {
        when(rainfallService.getRainfall(anyInt(), anyInt())).thenReturn(Mono.empty());

        ResponseEntity<RainfallEntity> response = rainfallController.getRainfall(10, 2023).block();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetMonthRandomYear() {
        RainfallEntity rainfallEntity = new RainfallEntity();
        rainfallEntity.setMonth(10);
        rainfallEntity.setYear(2020);
        rainfallEntity.setRainfallAmount(120.0);

        when(rainfallService.getMonthRandomYear(anyInt())).thenReturn(Mono.just(rainfallEntity));

        ResponseEntity<RainfallEntity> response = rainfallController.getMonthRandomYear(10).block();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rainfallEntity, response.getBody());
    }

    @Test
    void testGetMonthRandomYearNotFound() {
        when(rainfallService.getMonthRandomYear(anyInt())).thenReturn(Mono.empty());

        ResponseEntity<RainfallEntity> response = rainfallController.getMonthRandomYear(10).block();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
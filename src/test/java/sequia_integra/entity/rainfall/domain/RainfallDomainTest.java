package sequia_integra.entity.rainfall.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RainfallDomainTest {

    @Test
    void testNoArgsConstructor() {
        RainfallEntity rainfallEntity = new RainfallEntity();
        assertNotNull(rainfallEntity);
    }

    @Test
    void testAllArgsConstructor() {
        RainfallEntity rainfallEntity = new RainfallEntity();
        rainfallEntity.setId("1");
        rainfallEntity.setMonth(10);
        rainfallEntity.setYear(2023);
        rainfallEntity.setPrecipitation(100.0);

        assertEquals("1", rainfallEntity.getId());
        assertEquals(10, rainfallEntity.getMonth());
        assertEquals(2023, rainfallEntity.getYear());
        assertEquals(100.0, rainfallEntity.getPrecipitation());
    }

    @Test
    void testSettersAndGetters() {
        RainfallEntity rainfallEntity = new RainfallEntity();
        rainfallEntity.setId("1");
        rainfallEntity.setMonth(10);
        rainfallEntity.setYear(2023);
        rainfallEntity.setPrecipitation(100.0);

        assertEquals("1", rainfallEntity.getId());
        assertEquals(10, rainfallEntity.getMonth());
        assertEquals(2023, rainfallEntity.getYear());
        assertEquals(100.0, rainfallEntity.getPrecipitation());
    }
}
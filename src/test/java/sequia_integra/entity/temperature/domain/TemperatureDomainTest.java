package sequia_integra.entity.temperature.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureDomainTest {

    @Test
    void testNoArgsConstructor() {
        Temperature temperature = new Temperature();
        assertNotNull(temperature);
    }

    @Test
    void testAllArgsConstructor() {
        Temperature temperature = new Temperature("1", 2023, 10, 25.5);
        assertEquals("1", temperature.getTempId());
        assertEquals(2023, temperature.getYear());
        assertEquals(10, temperature.getMonth());
        assertEquals(25.5, temperature.getTemperature());
    }

    @Test
    void testSettersAndGetters() {
        Temperature temperature = new Temperature();
        temperature.setTempId("1");
        temperature.setYear(2023);
        temperature.setMonth(10);
        temperature.setTemperature(25.5);

        assertEquals("1", temperature.getTempId());
        assertEquals(2023, temperature.getYear());
        assertEquals(10, temperature.getMonth());
        assertEquals(25.5, temperature.getTemperature());
    }
}
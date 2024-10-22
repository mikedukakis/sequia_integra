package sequia_integra.entity.temperature.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureDto {

    private int year;
    private int month;
    private double temperature;
    private double avgYearTemp;



}
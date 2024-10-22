package sequia_integra.entity.temperature.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sequia_integra.entity.temperature.service.TemperatureService;

@RequiredArgsConstructor
@RequestMapping("/api/temp")
@RestController
public class TemperatureController {

    private final TemperatureService temperatureService;

    //@Operation(summary = "")
    @GetMapping("/month/{year}")
    public ResponseEntity<> getTempMonth()
    {

    }

    @GetMapping("/month/")
    public ResponseEntity<> getTempMonthRandomYear()
    {

    }


    @GetMapping("/hist")
    public ResponseEntity<> getHistoric()
    {

    }

}

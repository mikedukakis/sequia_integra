package sequia_integra.entity.rainfall.domain;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("api/rainfall")
public class RainfallController {
    private final RainfallService;

    @GetMapping("/")
}

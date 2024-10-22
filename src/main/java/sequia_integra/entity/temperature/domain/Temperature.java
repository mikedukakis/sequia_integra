package sequia_integra.entity.temperature.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "temperatures")
public class Temperature {

    @Id
    private String tempId;

    private int year;
    private int month;
    private double temperature;

}

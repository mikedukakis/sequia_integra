package sequia_integra.entity.rainfall.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "rainfall")
public class RainfallEntity {
    @Id
    private String id;
    private int Any;
    private int Mes;
    private double Precipitacions;

}

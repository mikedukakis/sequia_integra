package sequia_integra.entity.rainfall.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "rainfall")
public class RainfallEntity {
    @Id
    private String id;
    @Field("Month")
    private int month;
    @Field("Year")
    private int year;
    @Field("Precipitation")
    private double precipitation;

}

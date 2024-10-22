package sequia_integra.entity.rainfall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

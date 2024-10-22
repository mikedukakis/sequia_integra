package sequia_integra.entity.temperature.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "temperatures")
public class Temperature {

    @Id
    private String tempId;

    @Field("Year")
    private int year;

    @Field("Month")
    private int month;

    @Field("Temperature")
    private double temperature;

}

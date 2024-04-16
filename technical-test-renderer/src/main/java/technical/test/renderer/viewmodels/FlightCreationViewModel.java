package technical.test.renderer.viewmodels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonSerialize
public class FlightCreationViewModel {
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;
    private String image;
    private String origin;
    private String destination;
}


package technical.test.api.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import technical.test.api.facade.FlightFacade;
import technical.test.api.record.FlightRecord;
import technical.test.api.representation.FlightRepresentation;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightEndpoint {

    @Autowired
    private FlightFacade flightFacade;

    @GetMapping
    public Flux<FlightRepresentation> getAllFlights(@RequestParam(value = "sortBy", required = false, defaultValue = "price") String sortBy, @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        return flightFacade.getAllFlights(sortBy, page);
    }

    @PostMapping
    public void addFlight(@RequestBody FlightRecord flight) {
        flightFacade.addFlight(flight);
    }

}

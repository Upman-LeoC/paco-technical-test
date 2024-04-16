package technical.test.renderer.facades;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import technical.test.renderer.services.FlightService;
import technical.test.renderer.viewmodels.FlightCreationViewModel;
import technical.test.renderer.viewmodels.FlightViewModel;

@Component
public class FlightFacade {

    private final FlightService flightService;

    public FlightFacade(FlightService flightService) {
        this.flightService = flightService;
    }

    public Flux<FlightViewModel> getFlights(String sortBy, int page) {
        return this.flightService.getFlights(sortBy, page);
    }

    public void addFlight(FlightCreationViewModel flight) {
        this.flightService.addFlight(flight);
    }
}

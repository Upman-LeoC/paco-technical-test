package technical.test.renderer.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import technical.test.renderer.clients.TechnicalApiClient;
import technical.test.renderer.viewmodels.FlightCreationViewModel;
import technical.test.renderer.viewmodels.FlightViewModel;

@Service
public class FlightService {
    private final TechnicalApiClient technicalApiClient;

    public FlightService(TechnicalApiClient technicalApiClient) {
        this.technicalApiClient = technicalApiClient;
    }

    public Flux<FlightViewModel> getFlights(String sortBy, int page) {
        return this.technicalApiClient.getFlights(sortBy, page);
    }

    public void addFlight(FlightCreationViewModel flight) {
        this.technicalApiClient.addFlight(flight).subscribe(
                flightMono -> {
                    // Handle the successful response
                    System.out.println("Employee created");
                },
                error -> {
                    // Handle errors
                    System.err.println("Error creating employee: " + error.getMessage());
                }
        );;
    }

}

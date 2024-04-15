package technical.test.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import technical.test.api.record.FlightRecord;
import technical.test.api.repository.FlightRepository;
import technical.test.api.representation.FlightRepresentation;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public Flux<FlightRecord> getAllFlights(int page) {
        return flightRepository.findAllPaged(PageRequest.of(page, 6));
    }

    public void addFlight(FlightRecord flight) {

        flightRepository.save(flight).subscribe();

    }

}

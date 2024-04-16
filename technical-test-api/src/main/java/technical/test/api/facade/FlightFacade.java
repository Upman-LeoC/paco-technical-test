package technical.test.api.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.mapper.AirportMapper;
import technical.test.api.mapper.FlightMapper;
import technical.test.api.record.AirportRecord;
import technical.test.api.record.FlightRecord;
import technical.test.api.representation.FlightRepresentation;
import technical.test.api.services.AirportService;
import technical.test.api.services.FlightService;

import java.util.Comparator;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FlightFacade {
    private final FlightService flightService;
    private final AirportService airportService;
    private final FlightMapper flightMapper;
    private final AirportMapper airportMapper;

    public Flux<FlightRepresentation> getAllFlights(String sortBy, int page) {
        return flightService.getAllFlights(page, sortBy)
                .flatMap(flightRecord -> airportService.findByIataCode(flightRecord.getOrigin())
                        .zipWith(airportService.findByIataCode(flightRecord.getDestination()))
                        .flatMap(tuple -> {
                            AirportRecord origin = tuple.getT1();
                            AirportRecord destination = tuple.getT2();
                            FlightRepresentation flightRepresentation = this.flightMapper.convert(flightRecord);
                            flightRepresentation.setOrigin(this.airportMapper.convert(origin));
                            flightRepresentation.setDestination(this.airportMapper.convert(destination));
                            return Mono.just(flightRepresentation);
                        }));
//                .sort((flight1, flight2) -> {
//                    if (sortBy != null) {
//                        if ("price".equals(sortBy)) {
//                            return Double.compare(flight1.getPrice(), flight2.getPrice());
//                        } else if ("origin".equals(sortBy)) {
//                            return flight1.getOrigin().getName().compareTo(flight2.getOrigin().getName());
//                        } else if ("destination".equals(sortBy)) {
//                            return flight1.getDestination().getName().compareTo(flight2.getDestination().getName());
//                        }
//                    }
//                    return 0;
//                });
    }

    public void addFlight(FlightRecord flight) {

        flight.setId(UUID.randomUUID());
        flightService.addFlight(flight);

    }

}

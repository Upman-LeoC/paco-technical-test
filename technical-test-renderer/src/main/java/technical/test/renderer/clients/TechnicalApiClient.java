package technical.test.renderer.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.renderer.properties.TechnicalApiProperties;
import technical.test.renderer.viewmodels.FlightCreationViewModel;
import technical.test.renderer.viewmodels.FlightViewModel;

@Component
@Slf4j
public class TechnicalApiClient {

    private final TechnicalApiProperties technicalApiProperties;
    private final WebClient webClient;

    public TechnicalApiClient(TechnicalApiProperties technicalApiProperties, final WebClient.Builder webClientBuilder) {
        this.technicalApiProperties = technicalApiProperties;
        this.webClient = webClientBuilder.build();
    }

    public Flux<FlightViewModel> getFlights(String sortBy, int page) {
        return webClient
                .get()
                .uri(technicalApiProperties.getUrl() + technicalApiProperties.getFlightPath()
                        + "?page=" + page
                        + (sortBy != null && !sortBy.isEmpty() ? "&sortBy=" + sortBy : ""))
                .retrieve()
                .bodyToFlux(FlightViewModel.class);
    }

    public Mono<FlightCreationViewModel> addFlight(FlightCreationViewModel flight) {
        Mono<FlightCreationViewModel> flightMono = webClient
            .post()
            .uri(technicalApiProperties.getUrl() + technicalApiProperties.getFlightPath())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(flight), FlightCreationViewModel.class)
            .retrieve()
            .bodyToMono(FlightCreationViewModel.class);

        return flightMono;

    }
}

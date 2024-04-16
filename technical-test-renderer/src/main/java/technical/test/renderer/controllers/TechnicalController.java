package technical.test.renderer.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import technical.test.renderer.facades.FlightFacade;
import technical.test.renderer.viewmodels.FlightCreationViewModel;
import technical.test.renderer.viewmodels.FlightViewModel;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class TechnicalController {

    @Autowired
    private FlightFacade flightFacade;

    @GetMapping
    public Mono<String> getMarketPlaceReturnCouponPage(@RequestParam(value = "sortBy", required = false) String sortBy,
                                                       @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                       final Model model) {
        model.addAttribute("flights", this.flightFacade.getFlights(sortBy, page));
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("page", page);
        return Mono.just("pages/index");
    }

    @GetMapping("/new")
    public Mono<String> getNew(final Model model) {
        model.addAttribute("flight", new FlightCreationViewModel());
        return Mono.just("pages/new");
    }

    @PostMapping("/new")
    public Mono<String> postNew(@ModelAttribute FlightCreationViewModel flight, final Model model) {
        this.flightFacade.addFlight(flight);
        model.addAttribute("flight", new FlightCreationViewModel());
        return Mono.just("pages/new");
    }

}

package co.hrsquare.bindad.controller;

import co.hrsquare.bindad.controller.input.ClientDemoSignUpInput;
import co.hrsquare.bindad.controller.input.ClientFullSignUpInput;
import co.hrsquare.bindad.controller.output.ClientSummary;
import co.hrsquare.bindad.service.ClientOnboardingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = "/client",
        headers = {"Accept=application/json"},
        produces = {APPLICATION_JSON_VALUE}
)
@Slf4j
public class ClientController {

    private final ClientOnboardingService clientOnboardingService;

    public ClientController(ClientOnboardingService clientOnboardingService) {
        this.clientOnboardingService = clientOnboardingService;
    }

    @PostMapping("/newDemoClient")
    public String newClientDemo(ClientDemoSignUpInput input) {
        log.info("Handling newClientDemo request for {}", input);
        validateInput(input);

        String res = clientOnboardingService.signUpForDemo(input);

        log.info("DONE Handling newClientDemo request for {}", input);
        return res;
    }

    private void validateInput(ClientDemoSignUpInput input) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(input.getFirstName());
        Objects.requireNonNull(input.getLastName());
        Objects.requireNonNull(input.getEmailAddress());
        Objects.requireNonNull(input.getCompanyName());
        Objects.requireNonNull(input.getTelephone());
        Objects.requireNonNull(input.getPassword());
    }

    @GetMapping("/clientInfoSummary")
    public ClientSummary clientSummary(@RequestParam String clientPublicId) {
        log.info("Handling clientInfoSummary request for {}", clientPublicId);
        Objects.requireNonNull(clientPublicId);

        ClientSummary summary = clientOnboardingService.getClientSummary(clientPublicId);

        log.info("DONE Handling clientInfoSummary request for {}", clientPublicId);

        return summary;
    }

    @PostMapping("/newClient")
    public String newClient(ClientFullSignUpInput input) {
        log.info("Handling newClient request for {}", input);

        validateInput(input);
        String res = clientOnboardingService.newClient(input);

        log.info("DONE Handling newClient request for {}", input);
        return res;
    }

    private void validateInput(ClientFullSignUpInput input) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(input.getDemoEmailAddress());
        Objects.requireNonNull(input.getFirstName());
        Objects.requireNonNull(input.getLastName());
        Objects.requireNonNull(input.getEmailAddress());
        Objects.requireNonNull(input.getCompanyName());
        Objects.requireNonNull(input.getTelephone());
        Objects.requireNonNull(input.getPassword());
    }

    @PostMapping("/removeAllClientData")
    public String removeAllClientData(String clientEmail) {
        log.info("Handling removeAllClientData request for {}", clientEmail);
        Objects.requireNonNull(clientEmail);

        String res = clientOnboardingService.removeAllClientData(clientEmail);

        log.info("DONE Handling removeAllClientData request for {}", clientEmail);
        return res;
    }

}

package co.hrsquare.bindad.controller;

import co.hrsquare.bindad.controller.input.ClientDemoSignUpInput;
import co.hrsquare.bindad.controller.input.ClientUpgradeInput;
import co.hrsquare.bindad.controller.output.ClientSummary;
import co.hrsquare.bindad.service.ClientOnboardingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/upgradeClient")
    public String upgradeClient(ClientUpgradeInput input) {
        log.info("Handling upgradeClient request for {}", input);

        validateInput(input);
        String res = clientOnboardingService.upgradeClient(input);

        log.info("DONE Handling upgradeClient request for {}", input);
        return res;
    }

    private void validateInput(ClientUpgradeInput input) {
        Objects.requireNonNull(input);
        if (input.getClientPublicId() == null && input.getClientEmail() == null) {
            throw new IllegalArgumentException("Need either one of client public id or email");
        }
        Objects.requireNonNull(input.getContractStartDate());
        if (!input.isForcePastContractStart() && input.getContractStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Contract start date in the past");
        }
        Objects.requireNonNull(input.getCompanyName());
    }

    @PostMapping("/newLiveClient")
    public String newLiveClient() {
        return "SUCCESS";
    }

    @PostMapping("/removeAllClientData")
    public String removeAllClientData(String clientEmail) {
        log.info("Handling removeAllClientData request for {}", clientEmail);
        Objects.requireNonNull(clientEmail);

        String res = clientOnboardingService.removeAllClientData(clientEmail, false);

        log.info("DONE Handling removeAllClientData request for {}", clientEmail);
        return res;
    }

}

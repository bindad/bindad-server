package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.ClientDemoSignUpInput;
import co.hrsquare.bindad.controller.input.ClientFullSignUpInput;
import co.hrsquare.bindad.controller.input.ClientUpgradeInput;
import co.hrsquare.bindad.controller.output.ClientSummary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("local")
class ClientOnboardingServiceITest {
    @Autowired
    private ClientOnboardingService clientOnboardingService;

    @Test
    public void testSignUpForDemo() {
        clientOnboardingService.removeAllClientData("aseem.ruhela@myemail.com");

        ClientDemoSignUpInput input = new ClientDemoSignUpInput();
        input.setTitle("Mr");
        input.setFirstName("Aseem");
        input.setLastName("Ruhela");
        input.setEmailAddress("aseem.ruhela@myemail.com");
        input.setPassword("my-pass-123!");
        input.setCompanyName("Med Tech Inc.");
        input.setTelephone("+1 123 456 789");

        String demoClientId = clientOnboardingService.signUpForDemo(input);
        Assertions.assertNotNull(demoClientId);
        System.out.println("Demo client id: " + demoClientId);

        ClientSummary demoClientSummary = clientOnboardingService.getClientSummary(demoClientId);
        System.out.println(demoClientSummary);

        ClientFullSignUpInput fullInput = new ClientFullSignUpInput();
        fullInput.setTitle("Mr");
        fullInput.setFirstName("Aseem");
        fullInput.setLastName("Ruhela");
        fullInput.setEmailAddress("aseem.ruhela@myemail.com");
        fullInput.setDemoEmailAddress("aseem.ruhela@myemail.com");
        fullInput.setPassword("my-pass-123!");
        fullInput.setCompanyName("Med Tech Inc.");
        fullInput.setTelephone("+1 123 456 789");
        String liveClientId = clientOnboardingService.newClient(fullInput);
        Assertions.assertNotNull(liveClientId);
        System.out.println("Live client id: " + liveClientId);

        ClientSummary liveClientSummary = clientOnboardingService.getClientSummary(liveClientId);
        System.out.println(liveClientSummary);

//        clientOnboardingService.removeAllClientData("aseem.ruhela@myemail.com");
    }

}
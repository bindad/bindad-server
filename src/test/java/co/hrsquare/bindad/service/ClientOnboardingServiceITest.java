package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.ClientDemoSignUpInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class ClientOnboardingServiceITest {
    @Autowired
    private ClientOnboardingService clientOnboardingService;

    @Test
    public void testSignUpForDemo() {
        ClientDemoSignUpInput input = new ClientDemoSignUpInput();
        input.setTitle("Mr");
        input.setFirstName("Aseem");
        input.setLastName("Ruhela");
        input.setEmailAddress("aseem.ruhela@myemail.com");
        input.setPassword("my-pass-123!");
        input.setCompanyName("Med Tech Inc.");
        input.setTelephone("+1 123 456 789");

        String res = clientOnboardingService.signUpForDemo(input);
        Assertions.assertEquals("SUCCESS", res);
    }


}
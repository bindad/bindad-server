package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.ClientFullSignUpInput;
import co.hrsquare.bindad.controller.input.CompanyPayrollInput;
import co.hrsquare.bindad.controller.input.PensionSchemeInput;
import co.hrsquare.bindad.controller.input.PensionSchemesInput;
import co.hrsquare.bindad.controller.output.ClientSummary;
import co.hrsquare.bindad.model.company.payroll.PensionScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class CompanyPayrollServiceTest {

    @Autowired
    private ClientOnboardingService clientOnboardingService;
    @Autowired
    private CompanyPayrollService companyPayrollService;

    @Test
    public void testEditCompanyPayroll() {
        clientOnboardingService.removeAllClientData("aseem.ruhela@myemail.com");

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

        PensionSchemeInput pensionScheme = PensionSchemeInput.builder()
                .name("Axa Pension Scheme")
                .provider("Axa Financial Services")
                .build();

        CompanyPayrollInput input = CompanyPayrollInput.builder()
                .companyFullName("Med Tech Inc.")
                .payFrequency("MONTHLY")
                .monthlyPayPeriod("CALENDAR")
                .customPeriodStartDate(5)
                .useLastWorkingDay(true)
                .paymentMethod("Cash")
                .pensionSchemes(PensionSchemesInput.builder()
                        .pensionSchemeInputs(Collections.singletonList(pensionScheme))
                        .build())
                .build();

        String res = companyPayrollService.editPayroll(input);
        assertEquals("SUCCESS", res);
    }

}
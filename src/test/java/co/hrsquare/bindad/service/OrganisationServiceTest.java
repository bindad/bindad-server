package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.ClientDemoSignUpInput;
import co.hrsquare.bindad.controller.input.ClientFullSignUpInput;
import co.hrsquare.bindad.controller.input.DepartmentInput;
import co.hrsquare.bindad.controller.input.DepartmentsInput;
import co.hrsquare.bindad.controller.output.ClientSummary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class OrganisationServiceTest {

    @Autowired
    private ClientOnboardingService clientOnboardingService;
    @Autowired
    private OrganisationService organisationService;


    @Test
    public void testSignUpForDemo() {
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

        DepartmentInput legal = DepartmentInput.builder().fullName("Legal").build();
        DepartmentInput hr = DepartmentInput.builder().fullName("Human Resources").publicId("HR").build();
        DepartmentInput ops = DepartmentInput.builder().fullName("Operations").publicId("Ops").build();
        DepartmentsInput departmentsInput = DepartmentsInput.builder()
                .departments(Arrays.asList(legal, hr, ops))
                .orgFullName(liveClientSummary.getCompanyName())
                .build();

        organisationService.createOrUpdateDepartments(departmentsInput);

//        clientOnboardingService.removeAllClientData("aseem.ruhela@myemail.com");
    }



}
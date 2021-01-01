package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.*;
import co.hrsquare.bindad.controller.output.ClientSummary;
import co.hrsquare.bindad.model.employee.Title;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles("local")
class CompanyServiceTest {

    @Autowired
    private ClientOnboardingService clientOnboardingService;
    @Autowired
    private CompanyService companyService;


    @Test
    public void testSignUpForDemo() {
        clientOnboardingService.removeAllClientData("aseem.ruhela@myemail.com");

        //1. create account
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


        //2. update details
        DepartmentInput legal = DepartmentInput.builder().fullName("Legal").build();
        DepartmentInput hr = DepartmentInput.builder().fullName("Human Resources").publicId("HR").build();
        DepartmentInput ops = DepartmentInput.builder().fullName("Operations").publicId("Ops").build();
        DepartmentsInput departmentsInput = DepartmentsInput.builder()
                .departments(Arrays.asList(legal, hr, ops))
                .coFullName(liveClientSummary.getCompanyName())
                .build();

        FullNameDetailsInput adminNameDetails = FullNameDetailsInput.builder()
                .title(Title.Mr)
                .firstName("Aseem")
                .lastName("Ruhela")
                .build();

        EmailTelephoneInput adminContactDetails = EmailTelephoneInput.builder()
                .email("aseem.ruhela@myemail.com")
                .telephone("+1 123 456 789")
                .build();

        AddressInput primaryAddress = AddressInput.builder()
                .line1("32 Binary Street")
                .town("London")
                .country("UK")
                .postCode("UK1 TW2")
                .build();

        AddressInput addAddress1 = AddressInput.builder()
                .line1("1 Add Street")
                .town("London")
                .country("UK")
                .postCode("UK1 TW3")
                .build();

        CompanyInput companyInput = CompanyInput.builder()
                .tradingName("Med Tech Inc.")
                .registeredName("Med Tech Inc.")
                .website("http://www.medtech.com")
                .contactTelephone("+1 123 456 789")
                .partnership(false)
                .adminNameDetails(adminNameDetails)
                .adminContactDetails(adminContactDetails)
                .financeSameAsAdmin(true)
                .departmentsInput(departmentsInput)
                .primaryAddress(primaryAddress)
                .additionalAddress1(addAddress1)
                .build();

        companyService.editCompanyDetails(companyInput);
    }



}
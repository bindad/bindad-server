package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.ClientDemoSignUpInput;
import co.hrsquare.bindad.exception.InvalidInputException;
import co.hrsquare.bindad.mapper.*;
import co.hrsquare.bindad.model.auth.User;
import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.employee.Employee;
import co.hrsquare.bindad.model.organisation.Organisation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
public class ClientOnboardingService {
    private static final String SUCCESS = "SUCCESS";

    private final UserService userService;
    private final DataStore dataStore;
    private final IOrganisationMapper organisationMapper;


    public ClientOnboardingService(final UserService userService,
                                   final DataStore dataStore,
                                   final IOrganisationMapper organisationMapper) {
        this.userService = Objects.requireNonNull(userService);
        this.dataStore = Objects.requireNonNull(dataStore);
        this.organisationMapper = Objects.requireNonNull(organisationMapper);
    }

    @Transactional
    public String signUpForDemo(ClientDemoSignUpInput input) {
        //0. validate
        try {
            validateSignUpInput(input);
        } catch (InvalidInputException e) {
            log.error("signUpForDemo: invalid input", e);
            return e.getMessage();
        }

        //1. Create Client
        Client client = Client.createForDemo(
                input.getTitle(),
                input.getFirstName(),
                input.getLastName(),
                input.getEmailAddress(),
                input.getTelephone());
        dataStore.save(IClientMapper.class, client);

        //2. Create User (go through user service to encode pwd)
        User user = userService.save(input.getEmailAddress(), input.getPassword(), null);

        //3. Create Organisation
        Organisation org = Organisation.builder()
                .fullName(input.getCompanyName())
                .client(client)
                .deleted(false)
                .updatedBy(-1)
                .updatedTime(LocalDateTime.now())
                .build();
        dataStore.save(IOrganisationMapper.class, org);

        //4. Create Employee
        Employee employee = Employee.createOwner(
                input.getTitle(),
                input.getFirstName(),
                input.getLastName(),
                input.getEmailAddress(),
                input.getTelephone(),
                client,
                org,
                user);
        dataStore.save(IEmployeeMapper.class, employee);

        return SUCCESS;
    }

    private void validateSignUpInput(ClientDemoSignUpInput input) throws InvalidInputException {
        //check email not used
        if (userService.checkUsernameExists(input.getEmailAddress())) {
            throw new InvalidInputException("Email already in-use.");
        }

        //organisation not already present
        if (organisationMapper.findByFullName(input.getCompanyName()) != null) {
            throw new InvalidInputException("Company already in-use.");
        }
    }
}
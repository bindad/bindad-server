package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.ClientDemoSignUpInput;
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
    private final UserService userService;
    private final DataStore dataStore;


    public ClientOnboardingService(final UserService userService,
                                   final DataStore dataStore) {
        this.userService = Objects.requireNonNull(userService);
        this.dataStore = Objects.requireNonNull(dataStore);
    }

    @Transactional
    public String signUpForDemo(ClientDemoSignUpInput input) {
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

        return "SUCCESS";
    }
}

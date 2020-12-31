package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.ClientDemoSignUpInput;
import co.hrsquare.bindad.controller.input.ClientFullSignUpInput;
import co.hrsquare.bindad.controller.output.ClientSummary;
import co.hrsquare.bindad.exception.InvalidInputException;
import co.hrsquare.bindad.mapper.*;
import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.client.ClientContractType;
import co.hrsquare.bindad.model.client.ContractStatus;
import co.hrsquare.bindad.model.employee.EmailTelephone;
import co.hrsquare.bindad.model.employee.Employee;
import co.hrsquare.bindad.model.organisation.Organisation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static co.hrsquare.bindad.controller.output.ClientSummary.NO_CLIENT_INFO;

@Component
@Slf4j
public class ClientOnboardingService {
    private static final String SUCCESS = "SUCCESS";

    private final UserService userService;
    private final DataStore dataStore;
    private final IOrganisationMapper organisationMapper;
    private final IClientMapper clientMapper;
    private final IEmployeeMapper employeeMapper;

    public ClientOnboardingService(final UserService userService,
                                   final DataStore dataStore,
                                   final IOrganisationMapper organisationMapper,
                                   final IClientMapper clientMapper,
                                   final IEmployeeMapper employeeMapper) {
        this.userService = Objects.requireNonNull(userService);
        this.dataStore = Objects.requireNonNull(dataStore);
        this.organisationMapper = Objects.requireNonNull(organisationMapper);
        this.clientMapper = Objects.requireNonNull(clientMapper);
        this.employeeMapper = Objects.requireNonNull(employeeMapper);
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
        Client client = Client.createNewForDemo(
                input.getTitle(),
                input.getFirstName(),
                input.getLastName(),
                input.getEmailAddress(),
                input.getTelephone());
        dataStore.save(IClientMapper.class, client);

        //2. Create Organisation
        Organisation org = Organisation.builder()
                .fullName(input.getCompanyName())
                .client(client)
                .deleted(false)
                .updatedBy(-1)
                .updatedTime(LocalDateTime.now())
                .build();
        dataStore.save(IOrganisationMapper.class, org);

        //3. Create Employee
        Employee employee = Employee.createOwner(
                input.getTitle(),
                input.getFirstName(),
                input.getLastName(),
                input.getEmailAddress(),
                input.getTelephone(),
                client,
                org);
        dataStore.save(IEmployeeMapper.class, employee);

        //4. Create User (go through user service to encode pwd)
        userService.createNewClientUser(
                input.getEmailAddress(),
                input.getPassword(),
                null,
                client,
                org,
                employee);

        return client.getPublicId();
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

    @Transactional
    public String removeAllClientData(String clientEmail) {
        EmailTelephone emailTelephone = EmailTelephone.builder().email(clientEmail).build();
        Client client = Client.builder().clientContactDetails(emailTelephone).build();

        //client
        Long clientId = clientMapper.findId(client);
        if (clientId == null) {
            return "Cannot find client";
        }

        dataStore.hardDeleteBy(IClientMapper.class, "deleteById", clientId);
        dataStore.hardDeleteBy(IOrganisationMapper.class, "deleteByClientId", clientId);
        dataStore.hardDeleteBy(IDepartmentMapper.class, "deleteByClientId", clientId);
        dataStore.hardDeleteBy(IEmployeeMapper.class, "deleteByClientId", clientId);
        dataStore.hardDeleteBy(IUserMapper.class, "deleteByClientId", clientId);

        return SUCCESS;
    }

    public ClientSummary getClientSummary(String clientPublicId) {
        Client query = Client.builder().publicId(clientPublicId).build();
        Client c = clientMapper.findBy(query);
        if (c == null) {
            return NO_CLIENT_INFO;
        }

        Organisation org = organisationMapper.findByClientId(c.getId());
        return ClientSummary.builder()
                .clientPublicId(c.getPublicId())
                .fullContactName(c.getClientNameDetails().fullName())
                .emailAddress(c.getClientContactDetails().getEmail())
                .companyName(Optional.ofNullable(org).map(Organisation::getFullName).orElse("<No company registered>"))
                .contractType(c.getClientContract().getClientContractType().name())
                .contractStatus(createStatus(c))
                .build();
    }

    private String createStatus(Client c) {
        if (ClientContractType.Demo == c.getClientContract().getClientContractType()) {
            if (ContractStatus.DEMO_EXPIRED == c.getClientContract().getContractStatus()) {
                return "Demo (expired)";
            } else {
                return c.getClientContract().getContractStatus() + " (Expires: " + c.getClientContract().getContractEndDate() + ")";
            }
        } else {
            return c.getClientContract().getContractStatus().name();
        }
    }

    @Transactional
    public String newClient(ClientFullSignUpInput input) {
        String demoEmailAddress = input.getDemoEmailAddress();
        if (demoEmailAddress != null) {
            EmailTelephone emailTelephone = EmailTelephone.builder().email(demoEmailAddress).build();
            Client queryClient = Client.builder().clientContactDetails(emailTelephone).build();

            Client client = clientMapper.findBy(queryClient);
            if (client == null) {
                log.warn("Could not find client data for demo: {}", demoEmailAddress);
            } else {
                //remove all demo account data (any entered employees, other data would be removed!)
                removeAllClientData(client.getClientContactDetails().getEmail());
            }
        }

        //1. Create Client
        Client client = Client.createNew(
                input.getTitle(),
                input.getFirstName(),
                input.getLastName(),
                input.getEmailAddress(),
                input.getTelephone());
        dataStore.save(IClientMapper.class, client);

        //2. Create Organisation
        Organisation org = Organisation.builder()
                .fullName(input.getCompanyName())
                .client(client)
                .deleted(false)
                .updatedBy(-1)
                .updatedTime(LocalDateTime.now())
                .build();
        dataStore.save(IOrganisationMapper.class, org);

        //3. Create Employee
        Employee employee = Employee.createOwner(
                input.getTitle(),
                input.getFirstName(),
                input.getLastName(),
                input.getEmailAddress(),
                input.getTelephone(),
                client,
                org);
        dataStore.save(IEmployeeMapper.class, employee);

        //4. Create User (go through user service to encode pwd)
        userService.createNewClientUser(
                input.getEmailAddress(),
                input.getPassword(),
                null,
                client,
                org,
                employee);


        return client.getPublicId();
    }
}

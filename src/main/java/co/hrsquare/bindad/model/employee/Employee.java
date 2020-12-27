package co.hrsquare.bindad.model.employee;

import co.hrsquare.bindad.model.*;
import co.hrsquare.bindad.model.auth.User;
import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.organisation.Organisation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements ISystemUser {
    //system id
    private long id;

    private EmployeeContactDetails contactDetails;
    private Address homeAddress;
    private EmployeeContract contract;
    private EmployeeJobDetails jobDetails;
    private EmployeeManagementDetails managementDetails;
    private EmployeeHours hours;
    private EmployeeWorkLocation workLocation;
    private EmployeePay employeePay;
    private EmployeePersonalDetails personalDetails;
    private EmployeeAllergyDetails allergyDetails;
    private EmergencyContact emergencyContact;
    private EmployeeHealthAndSafetyDetails healthAndSafetyDetails;

    private SystemSettings systemSettings;

    private User user;
    private Organisation organisation;
    private Client client;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

    public static Employee createOwner(String title, String firstName, String lastName, String emailAddress,
                                       String telephone, Client client, Organisation org, User user) {

        FullNameDetails fullNameDetails = FullNameDetails.builder()
                .title(Title.valueOf(title))
                .firstName(firstName)
                .lastName(lastName)
                .build();
        EmailTelephone workContact = EmailTelephone.builder()
                .email(emailAddress)
                .telephone(telephone)
                .build();
        EmployeeContactDetails contactDetails = EmployeeContactDetails.builder()
                .fullNameDetails(fullNameDetails)
                .workContact(workContact)
                .build();
        Employee employee = new Employee();
        employee.setContactDetails(contactDetails);
        employee.setClient(client);
        employee.setOrganisation(org);
        employee.setUser(user);

        employee.setDeleted(false);
        employee.setUpdatedBy(-1);
        employee.setUpdatedTime(LocalDateTime.now());

        return employee;
    }
}

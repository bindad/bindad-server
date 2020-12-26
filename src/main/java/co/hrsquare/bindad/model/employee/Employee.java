package co.hrsquare.bindad.model.employee;

import co.hrsquare.bindad.model.*;
import co.hrsquare.bindad.model.auth.User;
import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.organisation.Organisation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

}

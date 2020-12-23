package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee implements ISystemUser {
    //system id
    private String id;

    private EmployeeContactDetails contactDetails;
    private Company company;
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
    private HealthAndSafetySettings healthAndSafetySettings;
    private SystemSettings systemSettings;

}

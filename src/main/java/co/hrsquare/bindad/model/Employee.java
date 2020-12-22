package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    //system id
    private String id;

    private EmployeeContactDetails contactDetails;
    private String employeeId;
    private Company company;
    private Address homeAddress;
    private EmployeeContract contract;




}

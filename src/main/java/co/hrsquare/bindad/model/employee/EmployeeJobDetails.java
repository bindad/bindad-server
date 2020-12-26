package co.hrsquare.bindad.model.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeJobDetails {

    private String jobTitle;
    private String employeeId;
    private String department;
    private boolean owner;
    private boolean manager;
    private boolean apprentice;

}

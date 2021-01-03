package co.hrsquare.bindad.model.employee;

import co.hrsquare.bindad.model.company.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeJobDetails {

    private String jobTitle;
    private String employeeId;
    private Department department;
    private boolean owner;
    private boolean manager;
    private boolean apprentice;

}

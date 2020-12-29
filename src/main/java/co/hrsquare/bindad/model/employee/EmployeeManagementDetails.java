package co.hrsquare.bindad.model.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class EmployeeManagementDetails {

    private Employee reportsTo;
    private Set<Employee> directlyManages;


}

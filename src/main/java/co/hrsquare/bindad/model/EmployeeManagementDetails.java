package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class EmployeeManagementDetails {

    private String reportsToId;
    private Set<String> directlyManagesIds;


}

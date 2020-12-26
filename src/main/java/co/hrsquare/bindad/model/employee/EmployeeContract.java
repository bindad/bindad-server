package co.hrsquare.bindad.model.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeContract {

    private EmployeeContractType type;
    private LocalDate startDate;
    private LocalDate continuedServiceDate;
    private EmployeePermanentOrFixedTerm permanentOrFixedTerm;
    private LocalDate probationEndDate;
    private NoticePeriod employerNoticePeriod;
    private NoticePeriod employeeNoticePeriod;
    private boolean workPermitRequired;
    private LocalDate workPermitExpiry;

}

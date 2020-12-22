package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeContract {

    private ContractType type;
    private LocalDate startDate;
    private LocalDate continuedServiceDate;
    private EmployeePermanentOrFixedTerm permanentOrFixedTerm;
    private LocalDate probationEndDate;
    private NoticePeriod employerNoticePeriod;
    private NoticePeriod employeeNoticePeriod;

}

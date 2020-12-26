package co.hrsquare.bindad.model.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class P45Details {

    public enum CircumstancesAtStart {
        A_EmployeeFirstJobSince6April,
        B_EmployeeOnlyJobSince6April,
        B_EmployeeHasAnotherJob
    }

    private boolean received;
    private LocalDate leavingDate;
    private double totalPayToDate;
    private double totalTaxToDate;
    private String p45TaxCode;
    private boolean p45W1OrM1;

    //applicable only if no p45
    private CircumstancesAtStart circumstancesAtStart;

}

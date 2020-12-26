package co.hrsquare.bindad.model.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeePay {

    private double rate;
    private PayRateUnit payRateUnit;
    private double standardHoursInDay;

    private String paySchedule;
    private String nationalInsuranceNumber;
    private boolean director;
    private String taxCode;
    private boolean week1OrMonth1;
    private boolean studentLoan;
    private StudentLoanType studentLoanType;
    private boolean postGraduateLoan;
    private NicTableLetter nicTableLetter;
    private P45Details p45Details;
    private BankDetails bankDetails;
    private String pensionScheme;


}

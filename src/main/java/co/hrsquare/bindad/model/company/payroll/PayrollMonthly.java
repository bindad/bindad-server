package co.hrsquare.bindad.model.company.payroll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollMonthly {

    private MonthlyPayPeriod monthlyPayPeriod;
    //used if 'Specified monthly period...' is selected
    private int customPeriodStartDate;

    //Option 1
    private boolean useLastWorkingDay;

    //Option 2
    private boolean useSpecificDate;
    private int specificDateDay;
    private boolean specificDateDayCurrentMonth;
    private boolean specificDateBringForwardWeekend;

    //Option 3
    private boolean specificWeekday;
    //1st, 2nd, 3d... Last
    private String specificWeekdayDate;
    //Mon, Tues, Wed...
    private String specificWeekdayDay;
    private boolean specificWeekdayBringForwardWeekend;

}

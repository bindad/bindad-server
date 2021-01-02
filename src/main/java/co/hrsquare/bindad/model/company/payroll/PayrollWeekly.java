package co.hrsquare.bindad.model.company.payroll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollWeekly {

    private WeeklyPayPeriod weeklyPayPeriod;
    //Mon, Tues, Wed...
    private String weeklyPayDay;
    //current or following week
    private boolean weeklyPayDayCurrentWeek;
    private boolean bringForwardWeekend;

}

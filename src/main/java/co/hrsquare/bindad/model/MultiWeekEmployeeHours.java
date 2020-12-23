package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MultiWeekEmployeeHours extends EmployeeHours {

    //2, 3, 4
    private int numberOfWeeks;
    //index 0 -> 2, index 1 -> 3 and so on...
    private EmployeeWeek[] weeks;


}

package co.hrsquare.bindad.model.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeHours {

    private EmployeeHoursType hoursType;
    private EmployeeWeek everyWeek;
}

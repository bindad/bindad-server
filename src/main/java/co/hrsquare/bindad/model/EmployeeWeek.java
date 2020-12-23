package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeWeek {
    private HoursForDay monday;
    private HoursForDay tuesday;
    private HoursForDay wednesday;
    private HoursForDay thursday;
    private HoursForDay friday;
    private HoursForDay saturday;
    private HoursForDay sunday;

}

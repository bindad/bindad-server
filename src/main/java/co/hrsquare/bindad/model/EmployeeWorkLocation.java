package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class EmployeeWorkLocation {

    private Address location;
    private HomeWorkingType homeWorkingType;
    //day id start from 1 (1 = Monday)
    private Set<Integer> daysHomeWorking;
    private AdHocFromHome adHocFromHome;

}

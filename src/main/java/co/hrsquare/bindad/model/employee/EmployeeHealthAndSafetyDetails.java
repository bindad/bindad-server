package co.hrsquare.bindad.model.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeHealthAndSafetyDetails {

    private boolean appointedPerson;
    private boolean fireWarden;
    private boolean firstAider;
    private boolean keyHolder;

}

package co.hrsquare.bindad.model.employee;

import co.hrsquare.bindad.model.GdprSensitiveInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@GdprSensitiveInfo
public class EmployeeAllergyDetails {

    private String allergies;
    private String whatToDoIfAllergicReaction;
    private String foodIntolerances;
    private String dietaryPreferences;

}

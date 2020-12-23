package co.hrsquare.bindad.model;

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

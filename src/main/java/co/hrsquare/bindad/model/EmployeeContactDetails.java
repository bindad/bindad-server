package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeContactDetails {

    private EmployeeTitle title;
    private String firstName;
    private String knownAs;
    private String middleInitial;
    private String lastName;
    private String companyEmail;
    private String personalEmail;
    private String workTelephone;
    private String workTelephoneExtension;
    private String workMobile;
    private String homeTelephone;
    private String personalMobile;

}

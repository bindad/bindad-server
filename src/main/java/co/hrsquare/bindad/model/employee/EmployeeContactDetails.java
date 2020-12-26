package co.hrsquare.bindad.model.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeContactDetails {

    private FullNameDetails fullNameDetails;
    private EmailTelephone workContact;
    private EmailTelephone homeContact;

}
